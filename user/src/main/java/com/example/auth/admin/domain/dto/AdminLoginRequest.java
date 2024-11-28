package com.example.auth.admin.domain.dto;

public record AdminLoginRequest(
        String adminId,
        String password
) {
}
