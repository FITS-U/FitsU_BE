package com.example.log.service;

import com.example.log.domain.Log;
import com.example.log.global.CustomUserDetails;
import com.example.log.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final LogRepository logRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UUID userId;
        try {
            userId = UUID.fromString(username);
        } catch (IllegalArgumentException e) {
            throw new UsernameNotFoundException("유효하지 않은 사용자 ID 형식입니다.");
        }

        List<Log> users= logRepository.findByUserId(userId);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("해당 ID의 사용자를 찾을 수 없습니다.");
        }
        Log log = users.get(0);
        return new CustomUserDetails(log);
    }
}