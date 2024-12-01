package com.example.model.client;

import com.example.model.dto.LogResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "clickLog-service", url = "http://localhost:8088")
public interface ClickLogClient {
    @GetMapping("/api/v1/log")
    List<LogResponse> getLogs(@RequestHeader("Authorization") String authorization);
}
