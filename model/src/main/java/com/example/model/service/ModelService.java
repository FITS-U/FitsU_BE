package com.example.model.service;

import com.example.model.client.CategoryClient;
import com.example.model.client.ClickLogClient;
import com.example.model.dto.AdDataResponse;
import com.example.model.dto.CategoryResponse;
import com.example.model.dto.LogResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelService {
    private final CategoryClient categoryClient;
    private final ClickLogClient clickLogClient;

    public AdDataResponse getUserData(String authorization){
        List<CategoryResponse> categories = categoryClient.getCategoryByUserId(authorization);
        List<LogResponse> logs = clickLogClient.getLogs(authorization);
        return new AdDataResponse(categories, logs);
    }
}
