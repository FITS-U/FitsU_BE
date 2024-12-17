package com.example.auth.user.controller;

import com.example.auth.global.JwtUtils;
import com.example.auth.user.domain.dto.*;
import com.example.auth.user.service.UserService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

   @PostMapping("/verify-code")
   public ResponseEntity<String> verifyCode(@RequestBody VerificationRequest request){
       String token = userService.verifyCode(request.getPhoneNum(), request.getCode());
       return ResponseEntity.ok(token);
   }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, @RequestHeader("Authorization") String token) {
        String jwtToken = userService.login(request, token);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request, @RequestHeader("Authorization") String token) {
        String jwtToken = userService.register(request, token.substring(7));
        return ResponseEntity.ok(jwtToken);
    }

    @GetMapping("/validate-token")
    public String validateToken(@RequestHeader("Authorization") String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        return jwtUtils.parseToken(token); // 사용자 ID 반환
    }

    @GetMapping("/name")
    public ResponseEntity<UserNameResponse> getUserName(@RequestHeader("Authorization") String authorization) {
        UserNameResponse name = userService.getUserNameFromToken(authorization);
        return ResponseEntity.ok(name);

    }

    @DeleteMapping
    public void deleteUser(@RequestHeader("Authorization") String authorization) {
       userService.deleteUser(authorization.substring(7));
    }

    @PutMapping
    public UserResponse updateUser(@RequestHeader("Authorization") String authorization,
                                   @RequestBody UserRequest userRequest) {
       return userService.updateUserInfo(authorization.substring(7), userRequest);
    }
}
