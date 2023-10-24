package de.pizza.tomate.controller.dto;

import de.pizza.tomate.domain.Order;
import de.pizza.tomate.domain.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderDTO {
    private Integer id;
    private Integer userId;
    private String state;
    private OffsetDateTime orderDate;
    private Double service;
    private Double total;
    private List<PizzaDTO> pizzas;

    public static OrderDTO getInstance(Order order) {
        return getInstance(order, new ArrayList<>());
    }

    public static OrderDTO getInstance(Order order, List<Pizza> pizzas) {
        return new OrderDTO(order.getId(), order.getUser().getId(), order.getState().name(),
                order.getOrderDate(), order.getService(), order.getTotal(),
                PizzaDTO.getInstance(pizzas));
    }
}
