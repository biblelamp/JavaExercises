package com.onlineshop.service;

import com.onlineshop.controller.dto.ShopDTO;
import com.onlineshop.domain.Shop;
import com.onlineshop.repository.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public List<ShopDTO> findAll() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopDTO> result = new ArrayList<>();
        shops.forEach(shop -> result.add(ShopDTO.getInstance(shop)));
        return result;
    }

    public ShopDTO add(ShopDTO shopDTO) {
        Shop newShop = new Shop();
        newShop.setShopName(shopDTO.getShopName());
        newShop = shopRepository.save(newShop);
        return ShopDTO.getInstance(newShop);
    }

    public ShopDTO update(Integer id, ShopDTO shopDTO) {
        Optional<Shop> shop = shopRepository.findById(id);
        if (shop.isPresent()) {
            Shop updShop = shop.get();
            updShop.setShopName(shopDTO.getShopName());
            shopRepository.save(updShop);
            return ShopDTO.getInstance(updShop);
        }
        return null;
    }

    public ShopDTO delete(Integer id) {
        Optional<Shop> shop = shopRepository.findById(id);
        if (shop.isPresent()) {
            Shop delShop = shop.get();
            shopRepository.delete(delShop);
            return ShopDTO.getInstance(delShop);
        }
        return null;
    }
}
