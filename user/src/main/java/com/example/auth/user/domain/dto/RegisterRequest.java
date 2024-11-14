package com.example.auth.user.domain.dto;

import com.example.auth.user.domain.entity.User;

import java.util.UUID;

public record RegisterRequest(
        UUID userId,
        String userName,
        String RRNum,
        String phoneNum,
        boolean isMarried,
        boolean hasChild
) {
    public User toEntity() {
        return User.builder()
                .userId(UUID.randomUUID())
                .userName(userName)
                .RRNum(RRNum)
                .phoneNum(phoneNum)
                .isMarried(isMarried)
                .hasChild(hasChild)
                .build();
    }
}
