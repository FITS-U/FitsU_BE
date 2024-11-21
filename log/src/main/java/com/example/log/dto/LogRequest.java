package com.example.log.dto;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class LogRequest {
    private UUID userId;
    private LocalDateTime clickTime;
    private Long mainCtgId;
}
