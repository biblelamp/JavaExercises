package com.onlineshop.repository;

import com.onlineshop.domain.ProductInShop;
import com.onlineshop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInShopRepository extends JpaRepository<ProductInShop, Integer> {
    List<ProductInShop> findByShop(Shop shop);
}
