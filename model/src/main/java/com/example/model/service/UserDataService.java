package com.example.model.service;

import com.example.model.client.*;
import com.example.model.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDataService {
    private final CategoryClient categoryClient;
    private final ClickLogClient clickLogClient;
    private final ModelClient modelClient;
    private final WebClient webClient;
    private final UserClient userClient;
    private final TransactionClient transactionClient;

    public AdResponse getAdData(String authorization){
        List<CategoryResponse> categories = categoryClient.getCategoryByUserId(authorization);
        List<LogResponse> logs = clickLogClient.getLogs(authorization);

        UserInfoRequest request = new UserInfoRequest(categories, logs);

        String flaskApiUrl = "http://localhost:5000/advertisement";

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
        List<MonthlySpendDto> sumOfLast30Days = transactionClient.getSumOfLast30Days(authorization);

        UserRequest userRequest = new UserRequest(userName, sumOfLast30Days);

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
