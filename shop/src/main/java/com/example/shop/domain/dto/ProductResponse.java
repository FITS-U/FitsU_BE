package com.example.shop.domain.dto;

import com.example.shop.domain.entity.Product;
import com.example.shop.domain.entity.ProductStatus;

import java.util.Date;
import java.util.UUID;

public record ProductResponse(
        UUID userId,
        String title,
        String content,
        Double price,
        Date createdAt,
        Date updatedAt,
        ProductStatus status
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getUserId(),
                product.getTitle(),
                product.getContent(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getStatus()
        );
    }
}
