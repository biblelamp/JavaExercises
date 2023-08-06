package com.onlineshop.repository;

import com.onlineshop.domain.ProductInShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInShopRepository extends JpaRepository<ProductInShop, Integer> {
}
