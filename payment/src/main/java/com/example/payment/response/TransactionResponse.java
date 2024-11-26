package com.example.payment.response;

import com.example.payment.domain.Transaction;

import java.time.LocalDateTime;

public record TransactionResponse(
        Long transactionId,
        Double price,
        String recipient,
        LocalDateTime createdAt,
        Long accountId,
        String accName,
        Long mainCtgId,
        String mainCtgName,
        Long userCardId,
        String cardName,
        String transactionType

) {
    public static TransactionResponse from(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getPrice(),
                transaction.getRecipient(),
                transaction.getCreatedAt(),
                transaction.getAccountId(),
                transaction.getAccName(),
                transaction.getMainCtgId(),
                transaction.getMainCtgName(),
                transaction.getUserCardId(),
                transaction.getCardName(),
                transaction.getTransactionType()

        );
    }
}
