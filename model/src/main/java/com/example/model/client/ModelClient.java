package com.example.model.client;

import com.example.model.dto.AdResponse;
import com.example.model.dto.UserInfoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "clickLog-service", url = "http://localhost:8088")
public interface ModelClient {
    @GetMapping("/advertisement")
    AdResponse getAdDataFromModel(@RequestBody UserInfoRequest request);
}