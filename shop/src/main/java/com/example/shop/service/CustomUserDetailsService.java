package com.example.shop.service;


import com.example.shop.domain.entity.Product;
import com.example.shop.global.CustomUserDetails;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final ProductRepository productRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UUID userId;
        try {
            userId = UUID.fromString(username);
        } catch (IllegalArgumentException e) {
            throw new UsernameNotFoundException("유효하지 않은 사용자 ID 형식입니다.");
        }

        List<Product> products = productRepository.findByUserId(userId);
        if (products.isEmpty()) {
            throw new UsernameNotFoundException("해당 ID의 사용자를 찾을 수 없습니다.");
        }
        Product product = products.get(0);
        return new CustomUserDetails(product);
    }
}
