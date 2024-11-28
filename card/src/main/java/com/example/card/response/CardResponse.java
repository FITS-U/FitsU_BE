package com.example.card.response;

import com.example.card.domain.*;
import lombok.Builder;

@Builder
public record CardResponse(
        Long cardId,
        String cardName,
        String prevSales,
        String annualFee,
        String benefitTitle,
        String description,
        Long categoryId,
        String categoryName
        ) {

    public static CardResponse from(
            CardInfo cardInfo,
            Benefit benefit,
            Category category
            ) {
        return new CardResponse(
                cardInfo.getCardId(),
                cardInfo.getCardName(),
                cardInfo.getPrevSales(),
                cardInfo.getAnnualFee(),
                benefit.getBenefitTitle(),
                benefit.getDescription(),
                category.getCategoryId(),
                category.getCategoryName()
        );


    }

}
