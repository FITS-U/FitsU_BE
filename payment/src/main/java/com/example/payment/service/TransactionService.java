package com.example.payment.service;

import com.example.payment.dto.MonthlySpendDto;
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
    Page<TransactionResponse> getByAccountId(UUID userId, Long accountId);
    List<TransactionResponse> getCategoryPaymentDetails(UUID userId, Long mainCtgId);
    Double getMonthlySpending(UUID userId, int year, int month);
    List<MonthlySpendDto> getMonthlySpendingByCategoryId(UUID userId , int year , int month);
}