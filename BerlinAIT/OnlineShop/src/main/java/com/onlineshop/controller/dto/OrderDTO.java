package com.onlineshop.controller.dto;

import com.onlineshop.domain.Order;
import com.onlineshop.domain.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class OrderDTO {
    private final Integer orderId;
    private final CustomerDTO customer;
    private final ShopDTO shop;
    private final OffsetDateTime orderDate;
    private final OrderState state;

    public static OrderDTO getInstance(Order order) {
        return new OrderDTO(order.getOrderId(), CustomerDTO.getInstance(order.getCustomer()),
                ShopDTO.getInstance(order.getShop()), order.getOrderDate(), order.getState());
    }
}
