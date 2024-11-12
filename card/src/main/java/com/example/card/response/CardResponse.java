package com.example.card.response;

import com.example.card.domain.CardInfo;
import lombok.Builder;

@Builder
public record CardResponse(Long cardId, String cardName, Long bankId) {

    public static CardResponse from(CardInfo cardInfo) {
        return new CardResponse(cardInfo.getCardId(), cardInfo.getCardName(), cardInfo.getBankId());
    }
}
