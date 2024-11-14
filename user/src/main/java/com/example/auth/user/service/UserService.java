package com.example.auth.user.service;

import com.example.auth.user.domain.dto.LoginRequest;
import com.example.auth.user.domain.dto.RegisterRequest;

public interface UserService {

    String login(LoginRequest loginRequest);
    void register(RegisterRequest registerRequest);
}
