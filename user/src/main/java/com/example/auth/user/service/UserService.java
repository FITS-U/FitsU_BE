package com.example.auth.user.service;

import com.example.auth.user.domain.dto.*;

import java.util.UUID;

public interface UserService {
    String verifyCode(String userName, String certificationCode);
    String login(LoginRequest loginRequest, String certificationCode);
    String register(RegisterRequest registerRequest, String certificationCode);
    UserNameResponse getUserNameFromToken(String token);
    void deleteUser(String token);
    UserResponse updateUserInfo(String token, UserRequest userRequest);
}
