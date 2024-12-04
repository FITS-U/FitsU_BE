package com.example.payment.service;

import com.example.payment.domain.Transaction;
import com.example.payment.dto.MonthlyExpenseDto;
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
    List<MonthlySpendDto> getSumOfLast30Days(UUID userId, LocalDateTime startDate);
    TransactionResponse updateCategory(UUID userId, Transaction transaction, Long transactionId);
    List<MonthlyExpenseDto> getMonthlyExpense(UUID userId);
    List<TransactionResponse> getUpdatePayments(UUID userId, LocalDateTime lastFetchedTime);
    Page<TransactionResponse> getUpdatePaymentsByAccountId(UUID userId, Long accountId, LocalDateTime lastFetchedTime);
    List<TransactionResponse> getUpdateCategoryPayments(UUID userId, Long mainCtgId, LocalDateTime lastFetchedTime);
}
