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
    @GetMapping("users/{userId}")
    public List<TransactionResponse> getAllPayments(@PathVariable String userId,
                                                    @RequestHeader("Authorization") String authorization) throws AccessDeniedException {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        if(!UUID.fromString(currentUserId).equals(UUID.fromString(userId))) {
            throw new AccessDeniedException("본인의 계좌만 조회할 수 있습니다.");
        }

        return transactionService.getAllPayments(UUID.fromString(userId));
    }

    //각 입출금 내역 별 상세
    @GetMapping("/details/{transactionId}/users/{userId}")
    public List<TransactionResponse> getPaymentByTransactionId(@PathVariable String userId, @PathVariable Long transactionId) {
        return transactionService.getPaymentsDetails(UUID.fromString(userId), transactionId);
    }

    // 월 기준 총 지출
    @GetMapping("/mth-spend/users/{userId}")
    public Double getMonthSpend(@PathVariable String userId, @RequestParam int year, @RequestParam int month) {
        Double monthlySpending = transactionService.getMonthlySpending(UUID.fromString(userId), year, month);
        return monthlySpending;
    }

    // 한 계좌의 소비 내역
    @GetMapping("/users/{userId}/accounts/{accountId}")
    public ResponseEntity<Page<TransactionResponse>> getPaymentByAccountId(@PathVariable String userId,
                                                                           @PathVariable Long accountId,
                                                                           Pageable pageable,
                                                                           @RequestHeader("Authorization") String authorization) throws AccessDeniedException {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        if(!UUID.fromString(currentUserId).equals(UUID.fromString(userId))) {
            throw new AccessDeniedException("본인의 계좌만 조회할 수 있습니다.");
        }

        Page<TransactionResponse> response = transactionService.getByAccountId(UUID.fromString(userId), accountId);
        return ResponseEntity.ok().body(response);
    }

    // 한 카테고리의 결제 내역 목록
    @GetMapping("/users/{userId}/category/{mainCtgId}")
    public List<TransactionResponse> getPaymentsByCategory(@PathVariable String userId,
                                                           @PathVariable Long mainCtgId,
                                                           @RequestHeader String authorization) throws AccessDeniedException {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        if(!UUID.fromString(currentUserId).equals(UUID.fromString(userId))) {
            throw new AccessDeniedException("본인의 계좌만 조회할 수 있습니다.");
        }
        return transactionService.getCategoryPaymentDetails(UUID.fromString(userId), mainCtgId);
    }

    // 카테고리별 월 기준 총 지출
    @GetMapping("/mth-expenses-by-category/users/{userId}")
    public List<MonthlySpendDto> getMonthlySpending(@PathVariable String userId,
                                                    @RequestParam int year,
                                                    @RequestParam int month,
                                                    @RequestHeader String authorization) throws AccessDeniedException {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        if(!UUID.fromString(currentUserId).equals(UUID.fromString(userId))) {
            throw new AccessDeniedException("본인의 계좌만 조회할 수 있습니다.");
        }

        List<MonthlySpendDto> monthlySpending = transactionService.getMonthlySpendingByCategoryId(UUID.fromString(userId), year, month);
        return monthlySpending;
    }
}
