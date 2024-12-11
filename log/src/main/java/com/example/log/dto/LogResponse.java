package com.example.log.dto;

import com.example.log.domain.Log;

import java.time.LocalDateTime;

public record LogResponse(
        Long category_id,
        String eventType,
        LocalDateTime clickTime
) {
    public static LogResponse from(Log log) {
        return new LogResponse(
                log.getCategoryId(),
                log.getEventType(),
                log.getClickTime()
        );
    }
}
