package com.example.payment.domain;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private UUID userId;
    private String transactionType;
    private String recipient;
    private Double price;
    private LocalDateTime createdAt;
    private Long categoryId;
    private String categoryName;
    private Long accountId;
    private String accName;
    private Long userCardId;
    private String cardName;
}