package com.example.auth.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String userName;
    private String nickName;
    private String RRNum;
    private String phoneNum;
}
