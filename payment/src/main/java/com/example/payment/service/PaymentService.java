package com.example.payment.service;

import com.example.payment.domain.Payment;
import com.example.payment.response.PaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@Service
public interface PaymentService {
    List<PaymentResponse> getAllPayments(UUID userId);
    List<PaymentResponse> getPaymentsDetails(UUID userId, Long payId);
    Double getMonthSpend(UUID userId, LocalDateTime startDate, LocalDateTime endDate);
    Page<PaymentResponse> getByAccountId(UUID userId, Long accountId);
    Page<PaymentResponse> getCategoryPayment(UUID userId, LocalDateTime createdAt);
    List<PaymentResponse> getCategoryPaymentDetails(UUID userId, Long categoryId);
}
