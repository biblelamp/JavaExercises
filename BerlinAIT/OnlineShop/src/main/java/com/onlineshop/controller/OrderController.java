package com.onlineshop.controller;

import com.onlineshop.controller.dto.OrderDTO;
import com.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Операции с заказами (выполняет покупатель):
 * + получить список заказов
 * + создать заказ с 1 товаром
 * + добавить товар в заказ
 * + удалить товар из заказа
 * + отправить заказ на исполнение (изменить статус)
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @PostMapping("/create/{customerId}/{shopId}/{productId}")
    public OrderDTO createOrder(@PathVariable Integer customerId,
                                @PathVariable Integer shopId,
                                @PathVariable Integer productId) {
        return orderService.createOrder(customerId, shopId, productId);
    }

    @PutMapping("/add/{orderId}/{productId}")
    public OrderDTO addProduct(@PathVariable Integer orderId, @PathVariable Integer productId) {
        return orderService.addProduct(orderId, productId);
    }

    @DeleteMapping("/delete/{orderId}/{productId}")
    public OrderDTO deleteProduct(@PathVariable Integer orderId, @PathVariable Integer productId) {
        return orderService.deleteProduct(orderId, productId);
    }

    @PutMapping("/{orderId}/send")
    public OrderDTO sendOrder(@PathVariable Integer orderId) {
        return orderService.sendOrder(orderId);
    }
}
