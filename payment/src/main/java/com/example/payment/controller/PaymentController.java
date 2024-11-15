package com.example.payment.controller;

import com.example.payment.response.PaymentResponse;
import com.example.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PaymentController {
    private final PaymentService paymentService;

    // 입출금 내역 목록
    @GetMapping("/payments/{userId}")
    public List<PaymentResponse> getAllPayments(@PathVariable UUID userId) {
        return paymentService.getAllPayments(userId);
    }

    //각 입출금 내역 별 상세
    @GetMapping("/payments/details/{userId}/{payId}")
    public List<PaymentResponse> getPaymentByPayId(@PathVariable UUID userId, @PathVariable Long payId) {
        return paymentService.getPaymentsDetails(userId, payId);
    }

    @GetMapping("/payments/mth-spend/{userId}/{startDate}/{endDate}")
    public Double getMonthSpend(@PathVariable UUID userId, @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) {
        return paymentService.getMonthSpend(userId, startDate, endDate);
    }

    // 계좌별 소비 내역
    @GetMapping("/payments/{userId}/{accountId}")
    public ResponseEntity<Page<PaymentResponse>> getPaymentByAccountId(@PathVariable UUID userId, @PathVariable Long accountId, Pageable pageable) {
        Page<PaymentResponse> response = paymentService.getByAccountId(userId, accountId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/payments/category/{userId}/{categoryId}")
    public List<PaymentResponse> getPaymentsByCategory(@PathVariable UUID userId, @PathVariable Long categoryId) {
        return paymentService.getCategoryPaymentDetails(userId, categoryId);
    }
}
