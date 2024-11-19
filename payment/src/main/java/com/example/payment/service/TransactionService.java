package com.example.payment.service;

import com.example.payment.response.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public interface TransactionService {
    List<TransactionResponse> getAllPayments(String userId);
    List<TransactionResponse> getPaymentsDetails(String userId, Long transactionId);
    Double getMonthSpend(String userId, int months, int years);
    Page<TransactionResponse> getByAccountId(String userId, Long accountId);
    Double getCategoryPayment(String userId, int months, int years, Long categoryId);
    List<TransactionResponse> getCategoryPaymentDetails(String userId, Long categoryId);
}
