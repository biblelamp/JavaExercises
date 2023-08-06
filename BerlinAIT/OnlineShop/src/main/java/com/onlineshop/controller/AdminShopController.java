package com.onlineshop.controller;

import com.onlineshop.controller.dto.ShopDTO;
import com.onlineshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Администрирование записей БД:
 * + редактирование справочника стран (countries)
 * - редактирование справочника поставшиков (suppliers)
 * - редактирование справочника покупателей (customers)
 * + редактирование справочника товаров (products)
 * + редактирование справочника магазинов (shops)
 *
 * Управление товарными запасами:
 * - оформление поставки товара в магазин(ы)
 * - оформление отгрузки товара покупателю согласно заказу
 */
@RestController
@RequestMapping("/admin/shop")
public class AdminShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/all")
    public List<ShopDTO> findAllShop() {
        return shopService.findAll();
    }

    @PostMapping("/add")
    public ShopDTO addShop(@RequestBody ShopDTO shopDTO) {
        return shopService.add(shopDTO);
    }

    @PutMapping("/update/{id}")
    public ShopDTO updateShop(@PathVariable Integer id, @RequestBody ShopDTO shopDTO) {
        return shopService.update(id, shopDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ShopDTO deleteShop(@PathVariable Integer id) {
        return shopService.delete(id);
    }
}
