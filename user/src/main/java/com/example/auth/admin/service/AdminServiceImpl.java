package com.example.auth.admin.service;

import com.example.auth.admin.domain.dto.AdminLoginRequest;
import com.example.auth.admin.domain.entity.Admin;
import com.example.auth.admin.repository.AdminRepository;
import com.example.auth.global.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository repository;
    private final JwtUtils jwtUtils;

    @Override
    public String login(AdminLoginRequest loginRequest) {
        Optional<Admin> loginAdmin = repository.findByAdminId(loginRequest.adminId());
        if(loginAdmin.isEmpty())
            throw new RuntimeException("해당 아이디를 가진 관리자가 없습니다");
        Admin admin = loginAdmin.get();
        if(!Objects.equals(loginRequest.password(), admin.getPassword()))
            throw new RuntimeException("아이디와 비밀번호가 일치하지 않습니다");
        return jwtUtils.generateToken(admin.getAdminId());
    }
}
