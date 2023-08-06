package com.onlineshop.controller.dto;

import com.onlineshop.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductsDTO {
    private final List<ProductDTO> productsDTO;

    public static ProductsDTO getInstance(List<Product> products) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        products.forEach(product -> productDTOList.add(ProductDTO.getInstance(product)));
        return new ProductsDTO(productDTOList);
    }
}
