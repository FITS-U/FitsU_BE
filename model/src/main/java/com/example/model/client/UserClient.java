package com.example.model.client;


import com.example.model.dto.UserNameResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "user-service", url = "http://localhost:8092")
public interface UserClient {
    @GetMapping("api/v1/auth/name")
    UserNameResponse getUserName(@RequestHeader("Authorization") String authorization);
}

