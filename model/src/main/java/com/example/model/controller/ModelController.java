package com.example.model.controller;

import com.example.model.client.CategoryClient;
import com.example.model.dto.AdDataResponse;
import com.example.model.dto.CategoryResponse;
import com.example.model.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user-data")
public class ModelController {
    private final ModelService modelService;

    @GetMapping
    public AdDataResponse getUserData(@RequestHeader("Authorization") String authorization) {
        String token = authorization.substring(7);
        return modelService.getUserData(token);
    }

}
