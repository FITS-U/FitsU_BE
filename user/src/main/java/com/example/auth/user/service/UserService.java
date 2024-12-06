package com.example.auth.user.service;

import com.example.auth.user.domain.dto.LoginRequest;
import com.example.auth.user.domain.dto.RegisterRequest;
import com.example.auth.user.domain.dto.UserNameResponse;

import java.util.UUID;

public interface UserService {
    String verifyCode(String phoneNum, String certificationCode);
    String login(LoginRequest loginRequest, String certificationCode);
    String register(RegisterRequest registerRequest, String certificationCode);
    UserNameResponse getUserNameFromToken(String token);
}
