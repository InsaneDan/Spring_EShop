package ru.isakov.market.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Slf4j
public class ServiceTimer {
    private final Map<String, Long> timer = new ConcurrentHashMap<>();

    public String timerToString() {
        StringBuilder outputString = new StringBuilder();
        if (timer.size() > 0) {
            timer.forEach((key, value) -> outputString.append(key).append(": ").append(value).append(" ms").append("<br>").append(System.lineSeparator()));
        } else {
            outputString.append("Timer is not set");
        }
        return outputString.toString();
    }

    @Around("execution(public * ru.isakov.market.services.*.*(..))")
    public Object measureTimer(ProceedingJoinPoint joinPoint ) {
        long start = System.currentTimeMillis();
        Object output = null;
        try {
            output = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;

        String serviceName = joinPoint.getSignature().getDeclaringType().getSimpleName();

        timer.merge(serviceName, time, Long::sum);

        return output;
    }


}
