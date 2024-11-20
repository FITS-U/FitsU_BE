package com.example.payment.response;

import com.example.payment.domain.Transaction;

import java.time.LocalDateTime;

public record TransactionResponse(
        Long transactionId,
        Double price,
        String recipient,
        LocalDateTime createdAt,
        Long accountId,
        Long mainCtgId,
        Long userCardId,
        String transactionType
) {
    public static TransactionResponse from(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getPrice(),
                transaction.getRecipient(),
                transaction.getCreatedAt(),
                transaction.getAccountId(),
                transaction.getMainCtgId(),
                transaction.getUserCardId(),
                transaction.getTransactionType()
        );
    }
}
