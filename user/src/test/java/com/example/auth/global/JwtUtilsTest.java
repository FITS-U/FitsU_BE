package com.example.auth.global;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JwtUtilsTest {

    @Autowired
    JwtUtils jwtUtils;

    @Test
    void parseToken() {
        String s = jwtUtils.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMTAyNTY1MDgxMSIsImV4cCI6MTc2NTMyOTEzOX0.axw5jdm79spYNkkk4iYPEA0Wt09JHll__ipD6YJqACE");
        System.out.println(s);
    }
}