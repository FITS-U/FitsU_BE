package com.example.payment.service;

import com.example.payment.response.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public interface TransactionService {
    List<TransactionResponse> getAllPayments(UUID userId);
    List<TransactionResponse> getPaymentsDetails(UUID userId, Long transactionId);
    Double getMonthSpend(UUID userId, int months, int years);
    Page<TransactionResponse> getByAccountId(UUID userId, Long accountId);
    Double getCategoryPayment(UUID userId, int months, int years, Long categoryId);
    List<TransactionResponse> getCategoryPaymentDetails(UUID userId, Long categoryId);
}
