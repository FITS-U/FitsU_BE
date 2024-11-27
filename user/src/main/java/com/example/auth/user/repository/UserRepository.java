package com.example.auth.user.repository;

import com.example.auth.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByUserId(UUID userId);
    Optional<User> findByUserName(String userName);
    Optional<User> findByPhoneNum(String phoneNum);
}
