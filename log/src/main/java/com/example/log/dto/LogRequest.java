package com.example.log.dto;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class LogRequest {
    private Long categoryId;
    private String eventType;
}
