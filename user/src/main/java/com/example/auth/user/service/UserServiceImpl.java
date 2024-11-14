package com.example.auth.user.service;

import com.example.auth.global.JwtUtils;
import com.example.auth.user.domain.dto.LoginRequest;
import com.example.auth.user.domain.dto.RegisterRequest;
import com.example.auth.user.domain.entity.User;
import com.example.auth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Override
    public String login(LoginRequest loginRequest) {
        Optional<User> loginUser = userRepository.findByUserName(loginRequest.userName());
        if(loginUser.isEmpty())
            throw new RuntimeException("해당 이름을 가진 사용자가 없습니다");
        User user = loginUser.get();
        if(!Objects.equals(loginRequest.phoneNum(), user.getPhoneNum()))
            throw new RuntimeException("핸드폰번호가 일치하지 않습니다");

        return jwtUtils.generateToken(user.getUserName());
    }

    @Override
    public void register(RegisterRequest request) {
        Optional<User> userName = userRepository.findByUserName(request.userName());
        if (userName.isPresent()) {
            User user = userName.get();
            if (Objects.equals(request.phoneNum(), user.getPhoneNum()))
                throw new RuntimeException("이미 가입된 사용자입니다");
        }

        User loggedInUser = request.toEntity();
        userRepository.save(loggedInUser);
    }
}
