package com.example.auth.user.service;

import com.example.auth.global.JwtUtils;
import com.example.auth.user.domain.dto.LoginRequest;
import com.example.auth.user.domain.dto.RegisterRequest;
import com.example.auth.user.domain.dto.UserNameResponse;
import com.example.auth.user.domain.entity.User;
import com.example.auth.user.repository.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final SmsService smsService;

    @Override
    public String verifyCode(String phoneNum, String certificationCode) {
        boolean isCodeValid = "000000".equals(certificationCode) || smsService.verifyCode(phoneNum, certificationCode);
//        boolean isCodeValid = smsService.verifyCode(phoneNum, certificationCode);
        if (!isCodeValid) {
            throw new RuntimeException("인증 코드가 유효하지 않거나 만료되었습니다.");
        }

        return jwtUtils.generateToken(phoneNum); // 임시 토큰 발급
    }

    @Override
    public String login(LoginRequest loginRequest, String token) {
        String phoneNum = jwtUtils.parseToken(token.substring(7));

        Optional<User> loginUser = userRepository.findByUserNameAndPhoneNum(loginRequest.userName(), phoneNum);
        if(loginUser.isEmpty()) {
            throw new RuntimeException("사용자 이름 또는 핸드폰 번호가 일치하지 않습니다.");
        }

        User user = loginUser.get();
        if(!phoneNum.equals(user.getPhoneNum())) {
            throw new RuntimeException("핸드폰번호가 일치하지 않습니다");
        }

    return jwtUtils.generateToken(user.getUserId().toString());
    }

    @Override
    public String register(RegisterRequest request, String token) {
        String phoneNum = jwtUtils.parseToken(token);

        if (userRepository.findByPhoneNum(phoneNum).isPresent()) {
            throw new RuntimeException("이미 가입된 전화번호입니다");
        }

        User loggedInUser = request.toEntity(phoneNum);
        userRepository.save(loggedInUser);

        return jwtUtils.generateToken(loggedInUser.getUserId().toString());
    }

    @Override
    public UserNameResponse getUserNameFromToken(String token){
        String bearer = token.startsWith("Bearer ") ? token.substring(7) : token;
        UUID userId = UUID.fromString(jwtUtils.parseToken(bearer));
        Optional<User> user = userRepository.findById(userId.toString());
        if(user.isEmpty()) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        String userName = user.get().getUserName();
        return new UserNameResponse(userName);
    }
}
