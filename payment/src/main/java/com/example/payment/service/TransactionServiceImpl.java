package com.example.payment.service;

import com.example.payment.domain.Transaction;
import com.example.payment.repository.TransactionRepository;
import com.example.payment.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
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
    public List<TransactionResponse> getAllPayments(String userId) {
        List<Transaction> payments = transactionRepository.findAllByUserId(UUID.fromString(userId));
        List<TransactionResponse> list = payments.stream().map(TransactionResponse::from).toList();
        return list;
    }

    @Override
    public List<TransactionResponse> getPaymentsDetails(String userId, Long transactionId) {
        List<Transaction> payments = transactionRepository.findByUserIdAndTransactionId(UUID.fromString(userId), transactionId);
        List<TransactionResponse> list = payments.stream().map(TransactionResponse::from).toList();
        return list;
    }

    @Override
    public Double getMonthSpend(String userId, int months, int years) {
        YearMonth yearMonth = YearMonth.of(years, months);
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Transaction> transactions = transactionRepository.findByUserIdAndCreatedAtBetween(UUID.fromString(userId), startOfMonth, endOfMonth);
        double sum = transactions.stream()
                .filter(transaction -> "지출".equals(transaction.getTransactionType()))
                .mapToDouble(Transaction::getPrice)
                .sum();
        return sum;
    }


    @Override
    public Page<TransactionResponse> getByAccountId(String userId, Long accountId) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Transaction> payments = transactionRepository.findByUserIdAndAccountId(UUID.fromString(userId), accountId, pageable);
        return payments.map(TransactionResponse::from);
    }

    @Override
    public List<TransactionResponse> getCategoryPaymentDetails(String userId, Long categoryId) {
        List<Transaction> payments = transactionRepository.findByUserIdAndCategoryId(UUID.fromString(userId), categoryId);
        List<TransactionResponse> list = payments.stream().map(TransactionResponse::from).toList();
        return list;
    }

    @Override
    public Double getCategoryPayment(String userId, int months, int years, Long categoryId) {
        YearMonth yearMonth = YearMonth.of(years, months);
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Transaction> transactions = transactionRepository.findByUserIdAndCategoryIdAndCreatedAtBetween(UUID.fromString(userId), categoryId, startOfMonth, endOfMonth);
        double sum = transactions.stream()
                .filter(transaction -> "지출".equals(transaction.getTransactionType()))
                .mapToDouble(Transaction::getPrice)
                .sum();
        return sum;
    }
}
