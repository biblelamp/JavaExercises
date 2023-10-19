package de.pizza.tomate.controller.dto;

import de.pizza.tomate.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class OrderDTO {
    private Integer id;
    private Integer userId;
    private String state;
    private OffsetDateTime orderDate;
    private Double service;
    private Double total;

    public static OrderDTO getInstance(Order order) {
        return new OrderDTO(order.getId(), order.getUser().getId(), order.getState().name(),
                order.getOrderDate(), order.getService(), order.getTotal());
    }
}
