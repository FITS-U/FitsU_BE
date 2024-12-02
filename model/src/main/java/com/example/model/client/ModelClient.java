package com.example.model.client;

import com.example.model.dto.AdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "clickLog-service", url = "http://localhost:8088")
public interface ModelClient {
    @GetMapping("/advertisement")
    AdResponse getAdData();
}

