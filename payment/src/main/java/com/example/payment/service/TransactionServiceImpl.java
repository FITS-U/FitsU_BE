package com.example.payment.service;

import com.example.payment.domain.Transaction;
import com.example.payment.dto.MonthlySpendDto;
import com.example.payment.repository.TransactionRepository;
import com.example.payment.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionResponse> getMonthlyPayments(UUID userId, int year, int month) {
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1).minusSeconds(1);
        List<Transaction> payments = transactionRepository.findTransactionsByUserAndYearAndMonth(userId, startDate, endDate);
        return payments.stream().map(TransactionResponse::from).toList();
    }

    @Override
    public Page<TransactionResponse> getByAccountId(UUID userId, Long accountId) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Transaction> payments = transactionRepository.findByUserIdAndAccountIdOrderByCreatedAtDesc(userId, accountId, pageable);
        return payments.map(TransactionResponse::from);
    }

    @Override
    public List<TransactionResponse> getCategoryPaymentDetails(UUID userId, Long categoryId, int year, int month) {
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0 , 0);
        LocalDateTime endDate = startDate.plusMonths(1).minusSeconds(1);
        List<Transaction> payments = transactionRepository.findByUserIdAndCategoryIdAndCreatedAt(userId, categoryId, startDate, endDate);
        List<TransactionResponse> list = payments.stream()
                .map(TransactionResponse::from)
                .toList();
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
        return (totalMonthlySpending != null ? totalMonthlySpending : 0);
    }

    @Override
    public List<MonthlySpendDto> getSumOfLast30Days(UUID userId, LocalDateTime startDate) {
        return transactionRepository.findSumOfLast30Days(userId, startDate);
    }

    @Override
    public TransactionResponse updateCategory(UUID userId, Transaction transaction, Long transactionId) {
        Optional<Transaction> byId = transactionRepository.findById(transactionId);
        if (byId.isPresent()) {
            Transaction existingTransaction = byId.get();
            Transaction updatingTransaction = Transaction.builder()
                    .transactionId(existingTransaction.getTransactionId())
                    .price(existingTransaction.getPrice())
                    .recipient(existingTransaction.getRecipient())
                    .createdAt(existingTransaction.getCreatedAt())
                    .accountId(existingTransaction.getAccountId())
                    .accName(existingTransaction.getAccName())
                    .categoryId(transaction.getCategoryId())
                    .categoryName(transaction.getCategoryName())
                    .userCardId(existingTransaction.getUserCardId())
                    .cardName(existingTransaction.getCardName())
                    .transactionType(existingTransaction.getTransactionType())
                    .userId(existingTransaction.getUserId())
                    .build();
            Transaction save = transactionRepository.save(updatingTransaction);
            return TransactionResponse.from(save);
        } else {
            throw new RuntimeException("해당 결제 내역이 없습니다.");
        }
    }
}
