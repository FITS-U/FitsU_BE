package com.example.image.response;

import com.example.image.domain.CardImage;

public record CardImageResponse(
        String cardImageUrl
) {
    public static CardImageResponse from(CardImage cardImage) {
        return new CardImageResponse(
                cardImage.getCardImageUrl()
        );
    };
}
