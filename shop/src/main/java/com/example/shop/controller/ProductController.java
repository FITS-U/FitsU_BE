package com.example.shop.controller;

import com.example.shop.domain.dto.ProductRequest;
import com.example.shop.domain.dto.ProductResponse;
import com.example.shop.global.JwtUtils;
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
    private final JwtUtils jwtUtils;

    // 모든 상품 조회
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 상품ID로 상품 조회
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
        ProductResponse product = productService.getProduct(productId);
        return ResponseEntity.ok(product);
    }

    // 내 판매 내역
    @GetMapping("/my-sales")
    public ResponseEntity<List<ProductResponse>> getMySales(@RequestHeader("Authorization") String token) {
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
        String userId = jwtUtils.parseToken(jwt);
        List<ProductResponse> mySales = productService.getMySales(UUID.fromString(userId));
        return ResponseEntity.ok(mySales);
    }

    // 상품 등록
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.updateProduct(productId, request);
        return ResponseEntity.ok(productResponse);
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("게시물이 삭제되었습니다.");
    }
}
