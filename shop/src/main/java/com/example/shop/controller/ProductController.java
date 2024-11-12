package com.example.shop.controller;

import com.example.shop.domain.dto.ProductRequest;
import com.example.shop.domain.dto.ProductResponse;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
        ProductResponse product = productService.getProduct(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/my-sales")
    public ResponseEntity<List<ProductResponse>> getMySales(@RequestHeader UUID userId) {
        List<ProductResponse> mySales = productService.getMySales(userId);
        return ResponseEntity.ok(mySales);
    }

    // 403 error : 클라이언트 권한 없음
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(
            @RequestHeader UUID userId, @RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.addProduct(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    // 403 error
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestHeader UUID userId, @PathVariable Long productId, @RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.updateProduct(userId, productId, request);
        return ResponseEntity.ok(productResponse);
    }

    // 403 error
    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponse> deleteProduct(
            @RequestHeader UUID userId, @PathVariable Long productId) {
        productService.deleteProduct(userId, productId);
        return ResponseEntity.ok(null);
    }
}
