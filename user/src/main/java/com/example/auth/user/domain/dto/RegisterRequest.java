package com.example.auth.user.domain.dto;

import com.example.auth.user.domain.entity.User;
import lombok.Getter;

import java.util.UUID;

public record RegisterRequest(
        String userName,
        String RRNum,
        String phoneNum,
        String nickName
) {
    public User toEntity(String phoneNum) {
        return User.builder()
                .userName(userName)
                .RRNum(RRNum)
                .phoneNum(phoneNum)
                .nickName(nickName)
                .build();
    }
}
