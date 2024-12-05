package com.example.tosspay.dto;

import com.example.tosspay.entity.Payment;
import com.example.tosspay.entity.TossPaymentMethod;
import com.example.tosspay.entity.TossPaymentStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
public record PaymentApprovalResponse (
        TossPaymentStatus status,
        String tossPaymentKey,
        TossPaymentMethod method,
        String orderId,
        LocalDateTime requestAt,
        LocalDateTime approvedAt,
        Double totalAmount,
        Map<String, Object> balance
        ){
    public static PaymentApprovalResponse from(Payment payment, Map<String, Object> balance) {
        return new PaymentApprovalResponse(
                payment.getStatus(),
                payment.getTossPaymentKey(),
                payment.getMethod(),
                payment.getOrderId(),
                payment.getRequestedAt(),
                payment.getApprovedAt(),
                payment.getTotalAmount(),
                balance
        );
}
}
