package com.example.model.service;

import com.example.model.client.CategoryClient;
import com.example.model.client.ClickLogClient;
import com.example.model.client.ModelClient;
import com.example.model.dto.AdResponse;
import com.example.model.dto.CategoryResponse;
import com.example.model.dto.LogResponse;
import com.example.model.dto.UserInfoRequest;
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
}
