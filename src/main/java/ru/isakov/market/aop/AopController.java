package ru.isakov.market.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.isakov.market.aop.ServiceTimer;
import ru.isakov.market.error_handling.MarketError;
import ru.isakov.market.models.dtos.JwtRequest;
import ru.isakov.market.models.dtos.JwtResponse;
import ru.isakov.market.services.UserService;
import ru.isakov.market.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
public class AopController {
    public final ServiceTimer serviceTimer;

    @GetMapping("/statistic")
    public String showStatistic() {
        return serviceTimer.timerToString();
    }
}
