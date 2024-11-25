package com.example.auth.user.domain.dto;

public record LoginRequest(
        String userName,
        String phoneNum,
        String certificationCode
) {
}
