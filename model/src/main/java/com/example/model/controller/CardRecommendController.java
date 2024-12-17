package com.example.model.controller;

import com.example.model.dto.RecommendResponse;
import com.example.model.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/card-recommendation")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class CardRecommendController {
    private final UserDataService userDataService;

    @PostMapping
    public ResponseEntity<List<RecommendResponse>> getCardRecommendation(@RequestHeader("Authorization") String authorization) {
        List<RecommendResponse> recommendData = userDataService.getRecommendData(authorization);
        return ResponseEntity.ok(recommendData);
    }
}
