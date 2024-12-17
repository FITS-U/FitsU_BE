package com.example.auth.user.domain.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String userName;
    private String RRNum;
    private String phoneNum;
    private String nickName;
}
