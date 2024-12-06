package com.example.payment.controller;

import com.example.payment.domain.Transaction;
import com.example.payment.dto.MonthlyExpenseDto;
import com.example.payment.dto.MonthlySpendDto;
import com.example.payment.global.JwtUtils;
import com.example.payment.response.TransactionResponse;
import com.example.payment.service.AuthService;
import com.example.payment.service.TransactionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class TransactionController {
    private final TransactionService transactionService;
    private final AuthService authService;

    // 월 기준 입출금 내역 목록
    @GetMapping("/monthly")
    public List<TransactionResponse> getMonthlyPayments(@RequestHeader("Authorization") String authorization,
                                                    @RequestParam("year") int year, @RequestParam("month") int month) {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);
        return transactionService.getMonthlyPayments(UUID.fromString(userId), year, month);
    }

    // 월 기준 총 지출
    @GetMapping("/mth-spend")
    public Double getMonthSpend(@RequestHeader("Authorization") String authorization,
                                @RequestParam("year") int year, @RequestParam("month") int month) {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);
        return transactionService.getMonthlySpending(UUID.fromString(userId), year, month);
    }

    // 한 계좌의 소비 내역 > 정렬
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Page<TransactionResponse>> getPaymentByAccountId(@PathVariable("accountId") Long accountId,
                                                                           @RequestHeader("Authorization") String authorization) {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);
        Page<TransactionResponse> response = transactionService.getByAccountId(UUID.fromString(userId), accountId);
        return ResponseEntity.ok().body(response);
    }

    // 한 카테고리의 결제 내역 목록 > 정렬
    @GetMapping("/category/{categoryId}")
    public List<TransactionResponse> getPaymentsByCategory(@PathVariable("categoryId") Long categoryId,
                                                           @RequestHeader("Authorization") String authorization){

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);
        return transactionService.getCategoryPaymentDetails(UUID.fromString(userId), categoryId);
    }

    // 카테고리별 월 기준 총 지출
    @GetMapping("/mth-expenses-by-category")
    public List<MonthlySpendDto> getMonthlySpending(@RequestParam("year") int year,
                                                    @RequestParam("month") int month,
                                                    @RequestHeader("Authorization") String authorization){

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);
        return transactionService.getMonthlySpendingByCategoryId(UUID.fromString(userId), year, month);
    }

    @GetMapping("/expenses/last-30-days")
    public List<MonthlySpendDto> getTop3CategoriesByLast30Days(@RequestHeader("Authorization") String authorization){

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);
        LocalDateTime startDate = LocalDateTime.now().minusDays(30);
        return transactionService.getSumOfLast30Days(UUID.fromString(userId), startDate);
    }

    @PutMapping("/{transactionId}")
    public TransactionResponse updateCategory(@RequestHeader("Authorization") String authorization,
                                              @RequestBody Transaction transaction,
                                              @PathVariable("transactionId") Long transactionId) {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);
        return transactionService.updateCategory(UUID.fromString(userId), transaction, transactionId);
    }
}
