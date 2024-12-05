package com.example.tosspay.dto;

import com.example.tosspay.entity.TossPaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentApprovalResponse {
    private String status;
    private String tossPaymentKey;
    private TossPaymentMethod method;
    private String orderId;
    private LocalDateTime requestAt;
    private LocalDateTime approvedAt;
    private Double totalAmount;
}
