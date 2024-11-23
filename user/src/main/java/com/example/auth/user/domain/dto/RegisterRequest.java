package com.example.auth.user.domain.dto;

import com.example.auth.user.domain.entity.User;
import lombok.Getter;

import java.util.UUID;

public record RegisterRequest(
        UUID userId,
        String userName,
        String RRNum,
        String phoneNum,
        String nickName
) {
    public User toEntity() {
        return User.builder()
                .userId(UUID.randomUUID())
                .userName(userName)
                .RRNum(RRNum)
                .phoneNum(phoneNum)
                .nickName(nickName)
                .build();
    }
}
