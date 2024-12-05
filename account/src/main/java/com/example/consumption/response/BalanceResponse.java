package com.example.consumption.response;

import com.example.consumption.domain.Bank;
import com.example.consumption.domain.UserAccount;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BalanceResponse(
        UUID userId,
        String bankName,
        String accountNum,
        Double balance
) {
    public static BalanceResponse from(UserAccount userAccount, Bank bank) {
        return new BalanceResponse(
                userAccount.getUserId(),
                bank.getBankName(),
                userAccount.getAccountNum(),
                userAccount.getBalance()
        );
    }
}
