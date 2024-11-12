package com.example.consumption.response;

import com.example.consumption.domain.Bank;

public record BankResponse(Long bankId, String bankName) {

    public static BankResponse from (Bank bank){
        return new BankResponse(bank.getBankId(), bank.getBankName());
    }
}
