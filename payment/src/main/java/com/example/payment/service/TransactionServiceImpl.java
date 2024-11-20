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
    public List<TransactionResponse> getCategoryPaymentDetails(UUID userId, Long mainCtgId) {
        List<Transaction> payments = transactionRepository.findByUserIdAndMainCtgId(userId, mainCtgId);
        List<TransactionResponse> list = payments.stream().map(TransactionResponse::from).toList();
        return list;
    }


    @Override
    public List<MonthlySpendDto> getMonthlySpendingByCategoryId(UUID userId, int year, int month) {
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1).minusSeconds(1);
        List<Object[]> spending = transactionRepository.findMonthlySpendingByCreatedAt(userId, startDate, endDate);
        List<MonthlySpendDto> list = spending.stream().map(MonthlySpendDto::from).toList();
        return list;
    }

    @Override
    public Double getMonthlySpending(UUID userId, int year, int month) {
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1).minusSeconds(1);
        Double totalMonthlySpending = transactionRepository.findTotalMonthlySpending(userId, startDate, endDate);
        return totalMonthlySpending;
    }
}
