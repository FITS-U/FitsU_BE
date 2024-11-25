package com.example.shop.domain.dto;

import com.example.shop.domain.entity.Product;
import com.example.shop.domain.entity.ProductStatus;

import java.util.Date;
import java.util.UUID;

public record ProductRequest(
        UUID userId,
        String title,
        String content,
        Double price,
        ProductStatus status,
        Long mainCtgId
) {
    public Product toEntity() {
        return Product.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .price(price)
                .createdAt(new Date())
                .updatedAt(new Date())
                .status(this.status != null ? this.status : ProductStatus.AVAILABLE)
                .mainCtgId(mainCtgId)
                .build();
    }
}
