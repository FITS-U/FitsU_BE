package com.example.shop.service;

import com.example.shop.domain.dto.ProductRequest;
import com.example.shop.domain.dto.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductResponse> getAllProducts();
    ProductResponse getProduct(Long productId);
    ProductResponse addProduct(ProductRequest request, UUID userId);
    List<ProductResponse> getMySales(UUID userId);
    List<ProductResponse> getMyPurchases(UUID userId);
    ProductResponse updateProduct(Long productId, ProductRequest request, UUID userId);
    void deleteProduct(Long productId, UUID userId);
}