package de.pizza.tomate.service;

import de.pizza.tomate.controller.dto.OrderDTO;
import de.pizza.tomate.domain.Order;
import de.pizza.tomate.domain.OrderState;
import de.pizza.tomate.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaService pizzaService;

    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> result = new ArrayList<>(orders.size());
        orders.forEach(order -> result.add(OrderDTO.getInstance(order, pizzaService.pizzasByOrder(order))));
        return result;
    }

    public String changeState(Integer orderId, final String state) {
        Order order = orderRepository.findById(orderId).orElse(null);
        OrderState newState = OrderState.valueOf(state);
        if (order != null) {
            switch (order.getState()) {
                case NEW:
                    if (newState != OrderState.CONFIRMED) {
                        newState = order.getState();
                    }
                case CONFIRMED:
                    // TODO check logic
                case PAID:
                    // TODO check logic
                case DELIVERED:
                    //TODO check logic
                case CANCELED:
                    break;
            }
            if (newState != order.getState()) {
                order.setState(newState);
                orderRepository.save(order);
                return state;
            }
        }
        return null;
    }
}
