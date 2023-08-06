package com.onlineshop.controller.dto;

import com.onlineshop.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {
    private final Integer productId;
    private final CategoryDTO category;
    private final SupplierDTO supplier;
    private final String productName;
    private final String description;
    private final Float price;
    private final Boolean isDeleted;

    public static ProductDTO getInstance(Product product) {
        return new ProductDTO(product.getProductId(), CategoryDTO.getInstance(product.getCategory()),
                SupplierDTO.getInstance(product.getSupplier()), product.getProductName(), product.getDescription(),
                product.getPrice(), product.getIsDeleted());
    }
}
