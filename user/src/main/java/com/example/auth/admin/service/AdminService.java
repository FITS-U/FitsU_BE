package com.example.auth.admin.service;

import com.example.auth.admin.domain.dto.AdminLoginRequest;

public interface AdminService {

    String login(AdminLoginRequest request);
}
