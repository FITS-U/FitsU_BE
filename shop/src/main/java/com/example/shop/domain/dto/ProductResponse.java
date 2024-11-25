
package com.example.shop.domain.dto;

import com.example.shop.domain.entity.Product;
import com.example.shop.domain.entity.ProductStatus;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record ProductResponse(
        UUID userId,
        Long productId,
        String title,
        String content,
        Double price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        ProductStatus status,
        Long mainCtgId
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getUserId(),
                product.getProductId(),
                product.getTitle(),
                product.getContent(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getStatus(),
                product.getMainCtgId()
        );
    }
}