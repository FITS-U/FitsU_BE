package com.example.payment.controller;

import com.example.payment.dto.MonthlySpendDto;
import com.example.payment.global.JwtUtils;
import com.example.payment.response.TransactionResponse;
import com.example.payment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
@CrossOrigin("http://localhost:3000")
public class TransactionController {
    private final TransactionService transactionService;
    private final JwtUtils jwtUtils;

    // 입출금 내역 목록
    @GetMapping("/")
    public List<TransactionResponse> getAllPayments(@RequestHeader("Authorization") String authorization) throws AccessDeniedException {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        return transactionService.getAllPayments(UUID.fromString(currentUserId));
    }

    //각 입출금 내역 별 상세
    @GetMapping("/details/{transactionId}")
    public List<TransactionResponse> getPaymentByTransactionId(@PathVariable Long transactionId, @RequestHeader("Authorization") String authorization) throws AccessDeniedException {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        return transactionService.getPaymentsDetails(UUID.fromString(currentUserId), transactionId);
    }

    // 월 기준 총 지출
    @GetMapping("/mth-spend")
    public Double getMonthSpend(@RequestHeader("Authorization") String authorization ,@RequestParam int year, @RequestParam int month) throws AccessDeniedException {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        Double monthlySpending = transactionService.getMonthlySpending(UUID.fromString(currentUserId), year, month);
        return monthlySpending;
    }

    // 한 계좌의 소비 내역
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Page<TransactionResponse>> getPaymentByAccountId(@PathVariable Long accountId,
                                                                           Pageable pageable,
                                                                           @RequestHeader("Authorization") String authorization) throws AccessDeniedException {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);


        Page<TransactionResponse> response = transactionService.getByAccountId(UUID.fromString(currentUserId), accountId);
        return ResponseEntity.ok().body(response);
    }

    // 한 카테고리의 결제 내역 목록
    @GetMapping("/category/{mainCtgId}")
    public List<TransactionResponse> getPaymentsByCategory(@PathVariable Long mainCtgId,
                                                           @RequestHeader String authorization) throws AccessDeniedException {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        return transactionService.getCategoryPaymentDetails(UUID.fromString(currentUserId), mainCtgId);
    }

    // 카테고리별 월 기준 총 지출
    @GetMapping("/mth-expenses-by-category")
    public List<MonthlySpendDto> getMonthlySpending(@RequestParam int year,
                                                    @RequestParam int month,
                                                    @RequestHeader String authorization) throws AccessDeniedException {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        List<MonthlySpendDto> monthlySpending = transactionService.getMonthlySpendingByCategoryId(UUID.fromString(currentUserId), year, month);
        return monthlySpending;
    }
}
