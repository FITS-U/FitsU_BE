package com.example.auth.admin.controller;

import com.example.auth.admin.domain.dto.AdminLoginRequest;
import com.example.auth.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    public String login(@RequestBody AdminLoginRequest request) {
        return adminService.login(request);
    }
}
