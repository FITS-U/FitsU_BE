package com.example.payment.response;

import com.example.payment.domain.Transaction;

public record PaymentResponse(
        String recipient,
        Double price,
        String categoryName
) {
    public static PaymentResponse from(Transaction transaction) {
        return new PaymentResponse(
                transaction.getRecipient(),
                transaction.getPrice(),
                transaction.getCategoryName());
    }
}
