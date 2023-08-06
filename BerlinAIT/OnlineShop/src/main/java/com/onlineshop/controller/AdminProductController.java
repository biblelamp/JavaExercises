package com.onlineshop.controller;

import com.onlineshop.controller.dto.ProductDTO;
import com.onlineshop.controller.dto.ProductsDTO;
import com.onlineshop.controller.dto.ShopDTO;
import com.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ProductsDTO findAll() {
        return productService.findAll();
    }

    @PostMapping("/add")
    public ProductDTO addCountry(@RequestBody ProductDTO product) {
        return productService.add(product);
    }

    @PutMapping("/update/{id}")
    public ProductDTO updateShop(@PathVariable Integer id, @RequestBody ProductDTO product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public ProductDTO deleteShop(@PathVariable Integer id) {
        return productService.delete(id);
    }
}
