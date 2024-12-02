package com.example.payment.service;

import com.example.payment.domain.Transaction;
import com.example.payment.dto.MonthlySpendDto;
import com.example.payment.repository.TransactionRepository;
import com.example.payment.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionResponse> getAllPayments(UUID userId) {
        List<Transaction> payments = transactionRepository.findByUserId(userId);
        List<TransactionResponse> list = payments.stream().map(TransactionResponse::from).toList();
        return list;
    }

    @Override
    public List<TransactionResponse> getPaymentsDetails(UUID userId, Long transactionId) {
        List<Transaction> payments = transactionRepository.findByUserIdAndTransactionId(userId, transactionId);
        List<TransactionResponse> list = payments.stream().map(TransactionResponse::from).toList();
        return list;
    }


    @Override
    public Page<TransactionResponse> getByAccountId(UUID userId, Long accountId) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Transaction> payments = transactionRepository.findByUserIdAndAccountId(userId, accountId, pageable);
        return payments.map(TransactionResponse::from);
    }

    @Override
    public List<TransactionResponse> getCategoryPaymentDetails(UUID userId, Long categoryId) {
        List<Transaction> payments = transactionRepository.findByUserIdAndCategoryId(userId, categoryId);
        List<TransactionResponse> list = payments.stream().map(TransactionResponse::from).toList();
        return list;
    }


    @Override
    public List<MonthlySpendDto> getMonthlySpendingByCategoryId(UUID userId, int year, int month) {
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1).minusSeconds(1);
        return transactionRepository.findMonthlySpendingByCreatedAt(userId, startDate, endDate);
    }

    @Override
    public Double getMonthlySpending(UUID userId, int year, int month) {
        if(month < 1 || month > 12) {
            throw new IllegalArgumentException("월은 1에서 12 사이의 값이어야 합니다.");
        }
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1).minusSeconds(1);
        Double totalMonthlySpending = transactionRepository.findTotalMonthlySpending(userId, startDate, endDate);
        return (totalMonthlySpending != null ? totalMonthlySpending : 0.0);
    }

    @Override
    public List<MonthlySpendDto> getSumOfLast30Days(UUID userId, LocalDateTime startDate) {
        return transactionRepository.findSumOfLast30Days(userId, startDate);

    }
}
