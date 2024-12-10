package com.example.consumption.response;

import com.example.consumption.domain.UserAccount;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AccountResponse (
        Long accountId, String accountNum, String accName, Double balance, Long bankId, String bankName, String imageUrl, Boolean isLinked ) {

    public static AccountResponse from(UserAccount userAccount) {
        return new AccountResponse(
                userAccount.getAccountId(),
                userAccount.getAccountNum(),
                userAccount.getAccName(),
                userAccount.getBalance(),
                userAccount.getBank().getBankId(),
                userAccount.getBank().getBankName(),
                userAccount.getBank().getImageUrl(),
                userAccount.getIsLinked()
        );
    }
}



