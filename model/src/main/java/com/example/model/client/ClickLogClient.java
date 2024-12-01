package com.example.model.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "clicklog-service", url = "http://localhost:8086")
public interface ClickLogClient {
    @GetMapping("/api/v1/logs")
    public List<logResponse> getCategoryByUserId(@RequestHeader String authorization);
}
