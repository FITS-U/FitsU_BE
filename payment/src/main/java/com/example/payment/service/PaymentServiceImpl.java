package com.example.payment.service;

import com.example.payment.domain.Payment;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;

    @Override
    public List<PaymentResponse> getAllPayments(UUID userId) {
        List<Payment> payments = paymentRepository.getAllByUserId(userId);
        List<PaymentResponse> list = payments.stream().map(PaymentResponse::from).toList();
        return list;
    }

    @Override
    public List<PaymentResponse> getPaymentsDetails(UUID userId, Long payId) {
        List<Payment> payments = paymentRepository.getPaymentsByUserIdAndPayId(userId, payId);
        List<PaymentResponse> list = payments.stream().map(PaymentResponse::from).toList();
        return list;
    }

    @Override
    public Double getMonthSpend(UUID userId, LocalDateTime startDate, LocalDateTime endDate) {
//        LocalDateTime startDate = ;
        return null;
    }

    @Override
    public Page<PaymentResponse> getByAccountId(UUID userId, Long accountId) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Payment> payments = paymentRepository.findByUserIdAndAccountId(userId, accountId, pageable);
        return payments.map(PaymentResponse::from);
    }

    @Override
    public Page<PaymentResponse> getCategoryPayment(UUID userId, LocalDateTime createdAt) {
        Pageable pageable = PageRequest.of(0,10);

        return null;
    }

    @Override
    public List<PaymentResponse> getCategoryPaymentDetails(UUID userId, Long categoryId) {
        List<Payment> payments = paymentRepository.findByUserIdAndCategoryId(userId, categoryId);
        List<PaymentResponse> list = payments.stream().map(PaymentResponse::from).toList();
        return list;
    }
}
