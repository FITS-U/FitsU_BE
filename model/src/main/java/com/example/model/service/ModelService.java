package com.example.model.service;

import com.example.model.client.CategoryClient;
import com.example.model.client.ClickLogClient;
import com.example.model.client.ModelClient;
import com.example.model.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelService {
    private final CategoryClient categoryClient;
    private final ClickLogClient clickLogClient;
    private final ModelClient modelClient;

    public AdResponse getUserData(String authorization){
        List<CategoryResponse> categories = categoryClient.getCategoryByUserId(authorization);
        List<LogResponse> logs = clickLogClient.getLogs(authorization);
        UserInfoRequest request = new UserInfoRequest(categories, logs);
        return processAdData(request);
    }

    public AdResponse processAdData() {
        AdResponse adData = modelClient.getAdData();
        return adData;
    }
}
