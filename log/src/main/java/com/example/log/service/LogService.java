package com.example.log.service;

import com.example.log.domain.Log;
import com.example.log.dto.LogRequest;
import com.example.log.dto.LogResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface LogService {
    void saveLogs(LogRequest request, UUID userId);
    List<LogResponse> getLogs(UUID userId);
}
