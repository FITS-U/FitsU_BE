package com.example.log.controller;

import com.example.log.dto.LogRequest;
import com.example.log.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/log")
public class LogController {
    private final LogService logService;

    @PostMapping
    public void saveClickLogs(@RequestBody LogRequest request) {
        logService.saveLogs(request);
    }
}
