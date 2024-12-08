package com.example.model.service;

import com.example.model.client.*;
import com.example.model.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDataService {
    private final CategoryClient categoryClient;
    private final ClickLogClient clickLogClient;
    private final WebClient webClient;
    private final UserClient userClient;
    private final TransactionClient transactionClient;
    private final ObjectMapper objectMapper;

    public AdResponse getAdData(String authorization){

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

        String flaskApiUrl = "http://56.155.9.34:5000/generate_ads";
        AdResponse adResponse = webClient.post()
                .uri(flaskApiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AdResponse.class)
                .block();

        return adResponse;
    }

    public CardRecommendationResponse getRecommendData(String authorization){
        UserNameResponse userName = userClient.getUserName(authorization);
        List<MonthlySpendDto> sumOfLast30Days = transactionClient.getCategoriesByLast30Days(authorization);

        UserRequest userRequest = new UserRequest(userName, sumOfLast30Days);

        String json;

        try {
            json = objectMapper.writeValueAsString(userRequest);
            System.out.println("Request: " + json);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

        String flaskApiUrl = "http://15.168.20.238:9995/recommend";
        CardRecommendationResponse response = webClient.post()
                .uri(flaskApiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userRequest)
                .retrieve()
                .bodyToMono(CardRecommendationResponse.class)
                .block();

        return response;
    }
}