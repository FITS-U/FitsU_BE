package com.example.auth.user.service;

import com.example.auth.user.domain.dto.LoginRequest;
import com.example.auth.user.domain.dto.RegisterRequest;

public interface UserService {
    String verifyCode(String phoneNum, String certificationCode);
    String login(LoginRequest loginRequest, String certificationCode);
    void register(RegisterRequest registerRequest, String certificationCode);
}
