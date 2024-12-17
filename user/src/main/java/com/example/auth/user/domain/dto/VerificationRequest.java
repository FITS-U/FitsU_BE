package com.example.auth.user.domain.dto;

import lombok.Getter;

@Getter
public class VerificationRequest {
    private String phoneNum;
    private String code;
}
