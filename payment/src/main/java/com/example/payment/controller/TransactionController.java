package com.example.payment.controller;

import com.example.payment.response.TransactionResponse;
import com.example.payment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
@CrossOrigin("http://localhost:3000")
public class TransactionController {
    private final TransactionService transactionService;

    // 입출금 내역 목록
    @GetMapping("users/{userId}")
    public List<TransactionResponse> getAllPayments(@PathVariable String userId) {
        return transactionService.getAllPayments(UUID.fromString(userId));
    }

    //각 입출금 내역 별 상세
    @GetMapping("/details/{transactionId}/users/{userId}")
    public List<TransactionResponse> getPaymentByTransactionId(@PathVariable String userId, @PathVariable Long transactionId) {
        return transactionService.getPaymentsDetails(UUID.fromString(userId), transactionId);
    }

    // 월 지출 금액 /mth-spend/users/{userId}?months=1&year=2024
    @GetMapping("/mth-spend/users/{userId}")
    public Double getMonthSpend(@PathVariable String userId, @RequestParam int months, @RequestParam int years) {
        return transactionService.getMonthSpend(UUID.fromString(userId), months, years);
    }

    // 한 계좌의 소비 내역
    @GetMapping("/users/{userId}/accounts/{accountId}")
    public ResponseEntity<Page<TransactionResponse>> getPaymentByAccountId(@PathVariable String userId, @PathVariable Long accountId, Pageable pageable) {
        Page<TransactionResponse> response = transactionService.getByAccountId(UUID.fromString(userId), accountId);
        return ResponseEntity.ok().body(response);
    }

    // 한 카테고리의 세부 결제 내역
    @GetMapping("/users/{userId}/category/{categoryId}")
    public List<TransactionResponse> getPaymentsByCategory(@PathVariable String userId, @PathVariable Long categoryId) {
        return transactionService.getCategoryPaymentDetails(UUID.fromString(userId), categoryId);
    }

    // 월 기준 한 카테고리의 소비 내역 조회 /mth-spend/users/{userId}?months=1&year=2024
    @GetMapping("/mth-spend/users/{userId}/category/{categoryId}")
    public Double getCategoryPayments(@PathVariable String userId, @RequestParam int months, @RequestParam int years, @PathVariable Long categoryId) {
        return transactionService.getCategoryPayment(UUID.fromString(userId), months, years, categoryId);
    };
}
