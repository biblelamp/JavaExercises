package com.onlineshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "products_in_shop")
@Getter
@Setter
@NoArgsConstructor
public class ProductInShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productInShopId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Shop.class)
    @JoinColumn(name = "shopId", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    private Integer quantity;
}
