package com.example.payment.repository;

import com.example.payment.domain.Transaction;
import com.example.payment.dto.MonthlyExpenseDto;
import com.example.payment.dto.MonthlySpendDto;
import com.example.payment.response.TransactionResponse;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(UUID userId);
    Page<Transaction> findByUserIdAndAccountIdOrderByCreatedAtDesc(UUID userId , Long accountId, Pageable pageable);
    List<Transaction> findByUserIdAndCategoryIdOrderByCreatedAtDesc(UUID userId, Long categoryId);

    @Query(value = "SELECT t FROM Transaction t " +
            "WHERE t.userId = :userId " +
            "AND t.createdAt BETWEEN :startDate AND :endDate " +
            "ORDER BY t.createdAt DESC")
    List<Transaction> findTransactionsByUserAndYearAndMonth(@Param("userId") UUID userId,
                                                            @Param("startDate") LocalDateTime starDate,
                                                            @Param("endDate") LocalDateTime endDate);

    @Query(value= "select sum(t.price) from Transaction t " +
            "where t.transactionType = 'expense' " +
            "AND t.userId = :userId " +
            "AND t.createdAt BETWEEN :startDate AND :endDate ")
    Double findTotalMonthlySpending(@Param("userId") UUID userId,
                                    @Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT new com.example.payment.dto.MonthlySpendDto(t.categoryId, t.categoryName, sum(t.price)) from Transaction t "  +
            "where t.transactionType = 'expense' " +
            "AND t.userId = :userId " +
            "AND t.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY t.categoryId, t.categoryName " +
            "ORDER BY sum(t.price) DESC")
    List<MonthlySpendDto> findMonthlySpendingByCreatedAt(@Param("userId") UUID userId,
                                                         @Param("startDate") LocalDateTime startDate,
                                                         @Param("endDate") LocalDateTime endDate);

    @Query("SELECT new com.example.payment.dto.MonthlySpendDto(t.categoryId, t.categoryName, sum(t.price)) " +
            "FROM Transaction t " +
            "WHERE t.transactionType = 'expense' " +
            "AND t.userId = :userId " +
            "AND t.createdAt BETWEEN :startDate AND CURRENT_DATE " +
            "GROUP BY t.categoryId, t.categoryName " +
            "ORDER BY SUM(t.price) DESC")
    List<MonthlySpendDto> findSumOfLast30Days(@Param("userId") UUID userId,
                                              @Param("startDate") LocalDateTime startDate,
                                              Pageable pageable);
}
