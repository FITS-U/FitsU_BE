package com.example.model.controller;

import com.example.model.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user-info")
public class UserInfoController {
    private final ModelService modelService;

    @PostMapping
    public void processUserData(@RequestHeader("Authorization") String authorization){
        String token = authorization.substring(7);
        modelService.getUserData(token);
    }
}
