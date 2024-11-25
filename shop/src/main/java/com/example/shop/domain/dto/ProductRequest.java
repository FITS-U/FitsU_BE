package com.example.shop.domain.dto;

import com.example.shop.domain.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private UUID userId;
    private String title;
    private String content;
    private Double price;
    private ProductStatus status;
    private Long mainCtgId;

};
