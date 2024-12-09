package com.example.model.controller;

import com.example.model.dto.CardRecommendationResponse;
import com.example.model.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/card-recommendation")
public class CardRecommendController {
    private final UserDataService userDataService;

    @GetMapping
    public ResponseEntity<CardRecommendationResponse> getCardRecommendation(@RequestHeader("Authorization") String authorization) {
        CardRecommendationResponse recommendData = userDataService.getRecommendData(authorization);
        return ResponseEntity.ok(recommendData);
    }
}
