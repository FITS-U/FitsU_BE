package com.example.bankImage.response;

import com.example.bankImage.domain.BankImage;

public record BankImageResponse(
        String bankImageUrl
) {
    public static BankImageResponse from(BankImage bankImage) {
        return new BankImageResponse(bankImage.getBankImageUrl());
    }
}
