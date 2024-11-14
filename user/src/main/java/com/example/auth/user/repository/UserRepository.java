package com.example.auth.user.repository;

import com.example.auth.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUserName(String userName);
    Optional<User> findByPhoneNum(String phoneNum);
}
