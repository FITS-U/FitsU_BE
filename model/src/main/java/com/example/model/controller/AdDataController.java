package com.example.model.controller;

import com.example.model.dto.AdResponse;
import com.example.model.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/advertisement")
public class AdDataController {
    private final UserDataService userDataService;

    @GetMapping
    public ResponseEntity<AdResponse> getAdData(@RequestHeader("Authorization") String authorization) {
        AdResponse adData = userDataService.getAdData(authorization);
        return ResponseEntity.ok(adData);
    }

}
