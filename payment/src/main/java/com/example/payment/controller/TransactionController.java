package com.example.payment.controller;

import com.example.payment.domain.Transaction;
import com.example.payment.dto.MonthlySpendDto;
import com.example.payment.global.JwtUtils;
import com.example.payment.response.TransactionResponse;
import com.example.payment.service.AuthService;
import com.example.payment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class TransactionController {
    private final TransactionService transactionService;
    private final AuthService authService;

    // 입출금 내역 목록 > 정렬
    @GetMapping
    public List<TransactionResponse> getAllPayments(@RequestHeader("Authorization") String authorization) {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);

        return transactionService.getAllPayments(UUID.fromString(userId));
    }

    //각 입출금 내역 별 상세
    @GetMapping("/details/{transactionId}")
    public List<TransactionResponse> getPaymentByTransactionId(@PathVariable Long transactionId, @RequestHeader("Authorization") String authorization) {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);

        return transactionService.getPaymentsDetails(UUID.fromString(userId), transactionId);
    }

    // 월 기준 총 지출
    @GetMapping("/mth-spend")
    public Double getMonthSpend(@RequestHeader("Authorization") String authorization ,@RequestParam int year, @RequestParam int month) {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);

        Double monthlySpending = transactionService.getMonthlySpending(UUID.fromString(userId), year, month);
        return monthlySpending;
    }

    // 한 계좌의 소비 내역 > 정렬
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Page<TransactionResponse>> getPaymentByAccountId(@PathVariable Long accountId,
                                                                           Pageable pageable,
                                                                           @RequestHeader("Authorization") String authorization) {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);


        Page<TransactionResponse> response = transactionService.getByAccountId(UUID.fromString(userId), accountId);
        return ResponseEntity.ok().body(response);
    }

    // 한 카테고리의 결제 내역 목록 > 정렬
    @GetMapping("/category/{categoryId}")
    public List<TransactionResponse> getPaymentsByCategory(@PathVariable Long categoryId,
                                                           @RequestHeader String authorization){


        String token = authorization.substring(7);
        String userId = authService.validateUser(token);

        return transactionService.getCategoryPaymentDetails(UUID.fromString(userId), categoryId);
    }

    // 카테고리별 월 기준 총 지출
    @GetMapping("/mth-expenses-by-category")
    public List<MonthlySpendDto> getMonthlySpending(@RequestParam int year,
                                                    @RequestParam int month,
                                                    @RequestHeader String authorization){


        String token = authorization.substring(7);
        String userId = authService.validateUser(token);

        List<MonthlySpendDto> monthlySpending = transactionService.getMonthlySpendingByCategoryId(UUID.fromString(userId), year, month);
        return monthlySpending;
    }

    @GetMapping("/expenses/last-30-days")
    public List<MonthlySpendDto> getSumOfLast30Days(@RequestHeader String authorization){
        String token = authorization.substring(7);
        String userId = authService.validateUser(token);
        LocalDateTime startDate = LocalDateTime.now().minusDays(30);
        return transactionService.getSumOfLast30Days(UUID.fromString(userId), startDate);
    }

    @PutMapping("/{transactionId}")
    public TransactionResponse updateCategory(@RequestHeader String authorization, @RequestBody Transaction transaction, @PathVariable Long transactionId) {
        String token = authorization.substring(7);
        String userId = authService.validateUser(token);
        return transactionService.updateCategory(UUID.fromString(userId), transaction, transactionId);
    }
}
