package com.example.payment.repository;

import com.example.payment.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> getAllByUserId(UUID userId);
    List<Transaction> findByUserIdAndTransactionId(UUID userId, Long transactionId);
    Page<Transaction> findByUserIdAndAccountId(UUID userId , Long accountId, Pageable pageable);
    List<Transaction> findByUserIdAndCategoryId(UUID userId, Long categoryId);
    List<Transaction> findByUserIdAndCreatedAtBetween(UUID userId, LocalDateTime from, LocalDateTime to);
    List<Transaction> findByUserIdAndCategoryIdAndCreatedAtBetween(UUID userId, Long categoryId, LocalDateTime from, LocalDateTime to);
}
