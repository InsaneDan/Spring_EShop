package ru.isakov.market.models.components;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.isakov.market.error_handling.ResourceNotFoundException;
import ru.isakov.market.models.entities.OrderItem;
import ru.isakov.market.models.entities.Product;
import ru.isakov.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private BigDecimal sum;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem orderItem : items) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incrementQuantity();
                recalculate();
                return;
            }
        }

        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id + " (add to cart)"));
        items.add(new OrderItem(product));
        recalculate();
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    private void recalculate() {
        sum = BigDecimal.ZERO;
        for (OrderItem oi : items) {
            sum = sum.add(oi.getPrice());
        }
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
