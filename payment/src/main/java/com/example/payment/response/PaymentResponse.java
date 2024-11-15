package com.example.payment.response;

import com.example.payment.domain.Payment;

import java.util.Date;

public record PaymentResponse(
        Long payId,
        Double price,
        String recipient,
        Date createdAt,
        Long accountId,
        Long categoryId,
        Long userCardId
) {
    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getPayId(),
                payment.getPrice(),
                payment.getRecipient(),
                payment.getCreatedAt(),
                payment.getAccountId(),
                payment.getCategoryId(),
                payment.getUserCardId()
        );
    }
}
