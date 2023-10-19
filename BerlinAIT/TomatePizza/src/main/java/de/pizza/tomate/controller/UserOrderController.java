package de.pizza.tomate.controller;

import de.pizza.tomate.controller.dto.OrderCreate;
import de.pizza.tomate.controller.dto.OrderDTO;
import de.pizza.tomate.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @PostMapping("/create")
    public OrderDTO create(@RequestBody OrderCreate order) {
        return userOrderService.create(order.getUserId());
    }
}
