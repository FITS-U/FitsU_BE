package com.example.shop.service;

import com.example.shop.domain.dto.ProductRequest;
import com.example.shop.domain.dto.ProductResponse;
import com.example.shop.domain.entity.Product;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findByProductId(productId);
        return ProductResponse.from(product);
    }

    @Override
    public ProductResponse addProduct(UUID userId, ProductRequest request) {
        Product product = request.toEntity(userId);
        Product savedProduct = productRepository.save(product);
        return ProductResponse.from(savedProduct);
    }

    @Override
    public List<ProductResponse> getMySales(UUID userId) {
        return productRepository.findByUserId(userId)
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Override
    public List<ProductResponse> getMyPurchases(UUID userId) {
        return List.of();
    }

    @Override
    public ProductResponse updateProduct(UUID userId, Long productId, ProductRequest request) {
        Product product = productRepository.findByProductIdAndUserId(productId, userId);
        Product updatedProduct = Product.builder()
                .productId(product.getProductId())
                .userId(product.getUserId())
                .title(request.title())
                .content(request.content())
                .price(request.price())
                .createdAt(product.getCreatedAt())
                .updatedAt(new Date())
                .status(request.status())
                .build();
        productRepository.save(updatedProduct);
        return ProductResponse.from(updatedProduct);
    }

    @Override
    public void deleteProduct(UUID userId, Long productId) {
        Product product = productRepository.findByProductIdAndUserId(productId, userId);
        productRepository.delete(product);
    }
}
