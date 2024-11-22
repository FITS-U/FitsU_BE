package com.example.log.service;

import com.example.log.domain.Log;
import com.example.log.dto.LogRequest;
import com.example.log.repository.LogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;


    public void saveLogs(LogRequest request) {
        Log log = Log.builder()
                .userId(request.getUserId())
                .clickTime(request.getClickTime())
                .mainCtgId(request.getMainCtgId())
                .build();
        logRepository.save(log);
    }
}
