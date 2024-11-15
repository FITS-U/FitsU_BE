package com.example.payment.service;

import com.example.payment.domain.Payment;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
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
    public Double getMonthSpend(UUID userId) {
        return 0.0;
    }

//    @Override
//    public Double getMonthSpend(UUID userId, int month, int year) {
//        LocalDate startDate = LocalDate.of(year, month, 1);
//        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
//        paymentRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
//        return null;
//    }

    @Override
    public Page<PaymentResponse> getByAccountId(UUID userId, Long accountId) {
        Pageable pageable = PageRequest.of(0,10);
        Page<Payment> payments = paymentRepository.findByUserIdAndAccountId(userId, accountId, pageable);
        return payments.map(PaymentResponse::from);
    }

    @Override
    public List<PaymentResponse> getCategoryPayment(UUID userId) {
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
