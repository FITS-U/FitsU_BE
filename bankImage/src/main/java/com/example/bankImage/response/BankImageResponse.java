package com.example.bankImage.response;

import com.example.bankImage.domain.BankImage;

public record BankImageResponse(
        String imageUrl
) {
    public static BankImageResponse from(BankImage bankImage) {
        return new BankImageResponse(bankImage.getImageUrl());
    }
}
