package com.onlineshop.controller;

import com.onlineshop.controller.dto.ShopDTO;
import com.onlineshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/shop")
public class AdminShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/all")
    public List<ShopDTO> findAll() {
        return shopService.findAll();
    }

    @PostMapping("/add")
    public ShopDTO addShop(@RequestBody ShopDTO shopDTO) {
        return shopService.add(shopDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ShopDTO> updateShop(@PathVariable Integer id, @RequestBody ShopDTO shopDTO) {
        ShopDTO updShopDTO = shopService.update(id, shopDTO);
        if (updShopDTO != null) {
            return ResponseEntity.ok(updShopDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ShopDTO> deleteShop(@PathVariable Integer id) {
        ShopDTO delShopDTO = shopService.delete(id);
        if (delShopDTO != null) {
            return ResponseEntity.ok(delShopDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
