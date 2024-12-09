package com.example.model.controller;

import com.example.model.dto.AdResponse;
import com.example.model.service.UserDataService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/advertisement")
public class AdDataController {
    private final UserDataService userDataService;

    @PostMapping
    public List<AdResponse> getAdData(@RequestHeader("Authorization") String authorization) {
        List<AdResponse> adData = userDataService.getAdData(authorization);
        return adData;
    }
}
