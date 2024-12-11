package com.example.model.service;

import com.example.model.client.*;
import com.example.model.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDataService {
    private final CategoryClient categoryClient;
    private final ClickLogClient clickLogClient;
    private final WebClient webClient;
    private final TransactionClient transactionClient;
    private final ObjectMapper objectMapper;

    public List<AdResponse> getAdData(String authorization){

        List<CategoryResponse> categories = categoryClient.getCategoryByUserId(authorization);
        List<LogResponse> logs = clickLogClient.getLogs(authorization);

        UserInfoRequest request = new UserInfoRequest(categories, logs);

        String json;

        try {
            json = objectMapper.writeValueAsString(request);
            System.out.println("Request: " + json);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

        String flaskApiUrl = "http://15.152.44.222:3434/generate_ads";
        Flux<AdResponse> adResponseFlux = webClient.post()
                .uri(flaskApiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(AdResponse.class);
        List<AdResponse> adResponse = adResponseFlux.collectList().block(Duration.of(1, ChronoUnit.MINUTES));
        if (adResponse == null || adResponse.isEmpty()) {
            throw new RuntimeException("Flask API로부터 광고 데이터를 받지 못했습니다.");
        }
        System.out.println(adResponse);
        return adResponse;
    }

    public List<RecommendResponse> getRecommendData(String authorization){
        List<MonthlyPaymentDto> sumOfLast30Days = transactionClient.getLast30Days(authorization);
        UserRequest userRequest = new UserRequest(sumOfLast30Days);

        String json;

        try {
            json = objectMapper.writeValueAsString(userRequest);
            System.out.println("Request: " + json);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

        String flaskApiUrl = "http://15.152.44.222:9995/recommend";
        Flux<RecommendResponse> recommendResponseFlux = webClient.post()
                .uri(flaskApiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userRequest)
                .retrieve()
                .bodyToFlux(RecommendResponse.class);

        List<RecommendResponse> response = recommendResponseFlux.collectList().block(Duration.of(1, ChronoUnit.MINUTES));
        return response;
    }
}
