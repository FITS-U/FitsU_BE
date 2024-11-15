package com.example.payment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payId;
    private UUID userId;
    private String recipient;
    private Double price;
    private LocalDateTime createdAt;
    private Long categoryId;
    private Long accountId;
    private Long userCardId;
}
