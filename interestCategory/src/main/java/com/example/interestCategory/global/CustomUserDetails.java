package com.example.interestCategory.global;

import com.example.interestCategory.domain.CategoryOfInterest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final CategoryOfInterest categoryOfInterest;

    public CustomUserDetails(CategoryOfInterest categoryOfInterest) {
        this.categoryOfInterest = categoryOfInterest;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 정보를 반환합니다. (예시로 기본 ROLE_USER 설정)
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return null;
    }


    @Override
    public String getUsername() {
        // 사용자 이름 반환
        return categoryOfInterest.getUserId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}