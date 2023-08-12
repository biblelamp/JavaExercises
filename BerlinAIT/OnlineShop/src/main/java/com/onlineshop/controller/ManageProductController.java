package com.onlineshop.controller;

import com.onlineshop.controller.dto.OrderDTO;
import com.onlineshop.controller.dto.ProductInShopDTO;
import com.onlineshop.domain.OrderState;
import com.onlineshop.service.ManageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class ManageProductController {

    @Autowired
    private ManageProductService manageProductService;

    @GetMapping("/shop/{shopId}")
    public ProductInShopDTO findByShop(@PathVariable Integer shopId) {
        return manageProductService.findByShop(shopId);
    }

    @PostMapping("/addtoshop/{shopId}")
    public ProductInShopDTO addProductToShop(@PathVariable Integer shopId, @RequestBody ProductInShopDTO products) {
        return manageProductService.productToShop(shopId, products);
    }

    @PutMapping("/order/{orderId}/fromshop")
    public OrderDTO getProductsByOrder(@PathVariable Integer orderId) {
        return manageProductService.getProductsByOrder(orderId);
    }

    @PutMapping("/order/{orderId}/paid")
    public OrderDTO paidOrder(@PathVariable Integer orderId) {
        return manageProductService.changeOrderState(orderId, OrderState.PAID);
    }

    @PutMapping("/order/{orderId}/cancel")
    public OrderDTO cancelOrder(@PathVariable Integer orderId) {
        return manageProductService.changeOrderState(orderId, OrderState.CANCELED);
    }
}
