package com.example.auth.user.controller;

import com.example.auth.user.domain.dto.LoginRequest;
import com.example.auth.user.domain.dto.RegisterRequest;
import com.example.auth.user.service.UserService;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        userService.register(request);
    }
}
