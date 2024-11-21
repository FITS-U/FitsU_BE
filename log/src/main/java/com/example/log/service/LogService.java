package com.example.log.service;

import com.example.log.domain.Log;
import com.example.log.dto.LogRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogService {
    void saveLogs(LogRequest request);
}
