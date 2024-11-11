package com.example.consumption.response;

import com.example.consumption.domain.UserAccount;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AccountResponse (
        Long accountId, String accountNum, String accName, Double balance, UUID userId, Long bankId ) {

    public static AccountResponse from(UserAccount userAccount) {
        return new AccountResponse(
                userAccount.getAccountId(),
                userAccount.getAccountNum(),
                userAccount.getAccName(),
                userAccount.getBalance(),
                userAccount.getUserId(),
                userAccount.getBank().getBankId()
        );
    }
}



