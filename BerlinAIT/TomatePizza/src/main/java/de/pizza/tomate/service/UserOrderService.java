package de.pizza.tomate.service;

import de.pizza.tomate.controller.dto.OrderDTO;
import de.pizza.tomate.domain.Order;
import de.pizza.tomate.domain.OrderState;
import de.pizza.tomate.domain.User;
import de.pizza.tomate.repository.OrderRepository;
import de.pizza.tomate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class UserOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public OrderDTO create(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Order order = new Order();
            order.setUser(user);
            order.setOrderDate(OffsetDateTime.now());
            order.setState(OrderState.NEW);
            order.setService(0d);
            order.setTotal(0d);
            return OrderDTO.getInstance(orderRepository.save(order));
        }
        return null;
    }
}
