package com.example.card.response;

import com.example.card.domain.Category;

public record CategoryResponse(
        Long categoryId,
        String categoryName
) {
    public static CategoryResponse from(Category category) {
        return new CategoryResponse(
                category.getCategoryId(),
                category.getCategoryName()
        );
    }
}
