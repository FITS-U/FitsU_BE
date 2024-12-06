package com.example.image.response;

import com.example.image.domain.CardImage;
import lombok.Builder;

@Builder
public record CardImageResponse(
        String ImageURL
) {
    public static CardImageResponse from(CardImage cardImage) {
        return new CardImageResponse(
                cardImage.getImageURL()
        );
    };
}
