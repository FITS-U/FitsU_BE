package com.example.card.response;

import com.example.card.domain.Benefit;
import com.example.card.domain.CardInfo;
import com.example.card.domain.CardPerform;
import lombok.Builder;

@Builder
public record CardResponse(
        Long cardId,
        String cardName,
        Long bankId,
        String description,
        Long categoryId,
        Double discountRate,
        String prevSales,
        String annualFee,
        String maxDiscount
        ) {

    public static CardResponse from(CardInfo cardInfo, Benefit benefit, CardPerform cardPerform) {
        return new CardResponse(
                cardInfo.getCardId(),
                cardInfo.getCardName(),
                cardInfo.getBankId(),
                benefit.getDescription(),
                benefit.getCategoryId(),
                benefit.getDiscountRate(),
                cardPerform.getPrevSales(),
                cardPerform.getAnnualFee(),
                cardPerform.getMaxDiscount());
    }

}
