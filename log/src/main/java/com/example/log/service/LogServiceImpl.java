package com.example.log.service;

import com.example.log.domain.Log;
import com.example.log.dto.LogRequest;
import com.example.log.dto.LogResponse;
import com.example.log.repository.LogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;


    public void saveLogs(LogRequest request, UUID userId) {
        Log log = Log.builder()
                .userId(userId)
                .categoryId(request.getCategoryId())
                .eventType(request.getEventType())
                .build();
        logRepository.save(log);
    }

    @Override
    public List<LogResponse> getLogs(UUID userId) {
        List<Log> logs = logRepository.findByUserId(userId);
        return logs.stream().map(LogResponse::from).toList();
    }
}
