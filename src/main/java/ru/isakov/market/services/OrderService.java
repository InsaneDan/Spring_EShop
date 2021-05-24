package ru.isakov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isakov.market.models.entities.Order;
import ru.isakov.market.models.entities.OrderItem;
import ru.isakov.market.models.entities.User;
import ru.isakov.market.models.repositories.OrderRepository;
import ru.isakov.market.models.components.Cart;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public Order createOrderForCurrentUser(User user) {
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getSum());
        // todo распутать этот кусок
        order.setItems(cart.getItems());
        for (OrderItem oi : cart.getItems()) {
            oi.setOrder(order);
        }
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }
}
