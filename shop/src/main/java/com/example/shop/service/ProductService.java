package com.example.shop.service;

import com.example.shop.domain.dto.ProductRequest;
import com.example.shop.domain.dto.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductResponse> getAllProducts();
    ProductResponse getProduct(Long productId);
    ProductResponse addProduct(UUID userId, ProductRequest request);
    List<ProductResponse> getMySales(UUID userId);
    List<ProductResponse> getMyPurchases(UUID userId);
    ProductResponse updateProduct(UUID userId, Long productId, ProductRequest request);
    void deleteProduct(UUID userId, Long productId);
}
