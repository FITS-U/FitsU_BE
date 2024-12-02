package com.example.model.controller;

import com.example.model.dto.AdResponse;
import com.example.model.dto.UserInfoRequest;
import com.example.model.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user-info")
public class UserInfoController {
    private final ModelService modelService;

    @PostMapping
    public AdResponse getAdData(@RequestHeader("Authorization") String authorization, UserInfoRequest request){
        String token = authorization.substring(7);
        AdResponse adResponse = modelService.getUserData(token);
        return adResponse;
    }
}
