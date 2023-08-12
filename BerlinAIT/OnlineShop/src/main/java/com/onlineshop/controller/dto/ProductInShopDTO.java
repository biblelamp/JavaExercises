package com.onlineshop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ProductInShopDTO {

    private List<ProductItem> productItems = new ArrayList<>();

    public void addProductItem(Integer productId, Integer quantity) {
        ProductItem item = new ProductItem(productId, quantity);
        productItems.add(item);
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductItem {
        Integer productId;
        Integer quantity;
    }
}
