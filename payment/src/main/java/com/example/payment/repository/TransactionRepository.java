package com.example.payment.repository;

import com.example.payment.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(UUID userId);
    List<Transaction> findByUserIdAndTransactionId(UUID userId, Long transactionId);
    Page<Transaction> findByUserIdAndAccountId(UUID userId , Long accountId, Pageable pageable);
    List<Transaction> findByUserIdAndMainCtgId(UUID userId, Long mainCtgId);

    @Query(value= "select sum(t.price) from Transaction t " +
            "where t.transactionType = 'expense' " +
            "AND t.userId = :userId " +
            "AND t.createdAt BETWEEN :startDate AND :endDate ")
    Double findTotalMonthlySpending(UUID userId, LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "select t.mainCtgId, SUM(t.price) from Transaction t "  +
            "where t.transactionType = 'expense' " +
            "AND t.userId = :userId " +
            "AND t.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY t.mainCtgId")
    List<Object[]> findMonthlySpendingByCreatedAt(UUID userId, LocalDateTime startDate, LocalDateTime endDate);
}
