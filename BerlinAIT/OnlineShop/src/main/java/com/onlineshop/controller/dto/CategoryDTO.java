package com.onlineshop.controller.dto;

import com.onlineshop.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryDTO {
    private final Integer categoryId;
    private final String categoryName;
    private final String description;

    public static CategoryDTO getInstance(Category category) {
        return new CategoryDTO(category.getCategoryId(), category.getCategoryName(), category.getDescription());
    }
}
