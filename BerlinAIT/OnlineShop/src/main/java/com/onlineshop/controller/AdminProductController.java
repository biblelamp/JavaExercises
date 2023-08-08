package com.onlineshop.controller;

import com.onlineshop.controller.dto.ProductDTO;
import com.onlineshop.controller.dto.ProductsDTO;
import com.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ProductDTO updateProduct(@PathVariable Integer id, @RequestBody ProductDTO product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public ProductDTO deleteProduct(@PathVariable Integer id) {
        return productService.delete(id);
    }
}
