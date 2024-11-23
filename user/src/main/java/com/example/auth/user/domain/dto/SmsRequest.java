package com.example.auth.user.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmsRequest {

    @NotEmpty(message = "휴대폰 번호를 입력해주세요")
    private String phoneNum;
    private String message;
}