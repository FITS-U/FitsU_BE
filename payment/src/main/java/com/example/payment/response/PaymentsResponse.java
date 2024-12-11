package com.example.payment.response;

import com.example.payment.domain.Transaction;

public record PaymentsResponse(
        Double price,
        String categoryName
) {
    public static PaymentsResponse from(Transaction transaction) {
        return new PaymentsResponse(
                transaction.getPrice(),
                transaction.getCategoryName());
    }
}

