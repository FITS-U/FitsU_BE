package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LogResponse {
    private Long categoryId;
    private String eventType;
    private LocalDateTime clickTime;
}
