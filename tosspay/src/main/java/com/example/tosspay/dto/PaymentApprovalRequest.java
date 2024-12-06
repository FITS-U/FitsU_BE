package com.example.tosspay.dto;

import com.example.tosspay.entity.TossPaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentApprovalRequest {
    private String tossPaymentKey;
    private String orderId;
    private Double amount;
    private Long accountId;
    private TossPaymentMethod method;
}
