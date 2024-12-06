package com.example.payment.controller;

import com.example.payment.response.TransactionResponse;
import com.example.payment.service.AuthService;
import com.example.payment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final TransactionService transactionService;
    private final AuthService authService;

    // 모든 거래 내역 update
    @MessageMapping("/update")
    @SendTo("/topic/transactions")
    public List<TransactionResponse> updateTransactions(@Payload Map<String, Object> payload) {
        String token = (String) payload.get("token");
        LocalDateTime lastFetchedTime = LocalDateTime.parse((String) payload.get("lastFetchedTime"));
        String userId = authService.validateUser(token);
        return transactionService.getUpdatePayments(UUID.fromString(userId), lastFetchedTime);
    }

    // 계좌별 거래 내역 update
    @MessageMapping("/update/accounts")
    @SendTo("/topic/accounts")
    public Page<TransactionResponse> updateTransactionsByAccount(@Payload Map<String, Object> payload) {
        String token = (String) payload.get("token");
        Long accountId = ((Number) payload.get("accountId")).longValue();
        LocalDateTime lastFetchedTime = LocalDateTime.parse((String) payload.get("lastFetchedTime"));
        String userId = authService.validateUser(token.substring(7));
        return transactionService.getUpdatePaymentsByAccountId(UUID.fromString(userId), accountId, lastFetchedTime);
    }

    // 카테고리별 거래 내역 update
    @MessageMapping("/update/category")
    @SendTo("/topic/category")
    public List<TransactionResponse> updateCategoryPayments(@Payload Map<String, Object> payload) {
        String token = (String) payload.get("token");
        Long categoryId = ((Number) payload.get("categoryId")).longValue();
        LocalDateTime lastFetchedTime = LocalDateTime.parse((String) payload.get("lastFetchedTime"));
        String userId = authService.validateUser(token.substring(7));
        return transactionService.getUpdateCategoryPayments(UUID.fromString(userId), categoryId, lastFetchedTime);
    }

    // 각 거래 내역 상세 정보 update
    @MessageMapping("/update/details")
    @SendTo("/topic/details")
    public List<TransactionResponse> updateTransactionById(@Payload Map<String, Object> payload) {
        String token = (String) payload.get("token");
        Long transactionId = ((Number) payload.get("transactionId")).longValue();
        LocalDateTime lastFetchedTime = LocalDateTime.parse((String) payload.get("lastFetchedTime"));
        String userId = authService.validateUser(token.substring(7));
        return transactionService.getUpdatePaymentDetails(UUID.fromString(userId), transactionId, lastFetchedTime);
    }
}
