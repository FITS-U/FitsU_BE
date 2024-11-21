package com.example.log.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Log {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    private UUID userId;
    private LocalDateTime clickTime;
    private Long mainCtgId;
    private String eventType;
}
