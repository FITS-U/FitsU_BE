package com.example.auth.admin.repository;

import com.example.auth.admin.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByAdminId(String adminId);
}
