package de.pizza.tomate.controller;

import de.pizza.tomate.controller.dto.OrderDTO;
import de.pizza.tomate.service.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private AdminOrderService adminOrderService;

    @GetMapping("/all")
    public List<OrderDTO> findAll() {
        return adminOrderService.findAll();
    }

    @PutMapping("/{orderId}/state/{state}")
    public String changeState(@PathVariable Integer orderId, @PathVariable String state) {
        return adminOrderService.changeState(orderId, state);
    }
}
