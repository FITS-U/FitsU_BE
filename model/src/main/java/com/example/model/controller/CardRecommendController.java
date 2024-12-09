package com.example.model.controller;

import com.example.model.dto.CardRecommendationResponse;
import com.example.model.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/card-recommendation")
public class CardRecommendController {
    private final UserDataService userDataService;

    @PostMapping
    public ResponseEntity<CardRecommendationResponse> getCardRecommendation(@RequestHeader("Authorization") String authorization) {
        CardRecommendationResponse recommendData = userDataService.getRecommendData(authorization);
        return ResponseEntity.ok(recommendData);
    }
}
