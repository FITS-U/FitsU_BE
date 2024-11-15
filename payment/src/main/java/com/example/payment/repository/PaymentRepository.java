package com.example.payment.repository;

import com.example.payment.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> getAllByUserId(UUID userId);
    List<Payment> getPaymentsByUserIdAndPayId(UUID userId, Long payId);
    Page<Payment> findByUserIdAndAccountId(UUID userId , Long accountId, Pageable pageable);

    List<Payment> findByUserIdAndCategoryId(UUID userId, Long categoryId);
}
