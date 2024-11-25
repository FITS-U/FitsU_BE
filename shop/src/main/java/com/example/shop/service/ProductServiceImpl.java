package com.example.shop.service;

import com.example.shop.domain.dto.ProductRequest;
import com.example.shop.domain.dto.ProductResponse;
import com.example.shop.domain.entity.Product;
import com.example.shop.domain.entity.ProductStatus;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return ProductResponse.from(product);
    }

    @Override
    public ProductResponse addProduct(ProductRequest request, UUID userId) {
        Product product = Product.builder()
                .userId(userId)
                .title(request.getTitle())
                .content(request.getContent())
                .price(request.getPrice())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(ProductStatus.AVAILABLE)
                .mainCtgId(request.getMainCtgId())
                .build();

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
    public ProductResponse updateProduct(Long productId, ProductRequest request, UUID userId) {
        Product product = productRepository.findByProductId(productId);

        if (product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다.");
        }
        if (!product.getUserId().equals(userId)) {
            throw new RuntimeException("상품을 수정할 수 없습니다.");
        }

        Product updatedProduct = Product.builder()
                .productId(product.getProductId())
                .userId(product.getUserId())
                .title(request.getTitle())
                .content(request.getContent())
                .price(request.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .status(request.getStatus())
                .mainCtgId(request.getMainCtgId())
                .build();
        productRepository.save(updatedProduct);
        return ProductResponse.from(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId, UUID userId) {
        Product product = productRepository.findByProductId(productId);
        if (product == null) {
            throw new RuntimeException("상품을 찾을 수 없습니다.");
        }

        if (!product.getUserId().equals(userId)) {
            throw new RuntimeException("상품을 삭제할 수 없습니다.");
        }
        productRepository.delete(product);
    }
}