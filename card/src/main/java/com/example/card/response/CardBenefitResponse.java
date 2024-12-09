package com.example.card.response;

import com.example.card.domain.Benefit;
import com.example.card.domain.CardInfo;
import com.example.card.domain.Category;
import lombok.Builder;

@Builder
public record CardBenefitResponse(
        Long cardId,
        String cardName,
        String benefitTitle,
        Long categoryId
) {

    public static CardBenefitResponse from(
            CardInfo cardInfo,
            Benefit benefit,
            Category category
    ) {
        return new CardBenefitResponse(
                cardInfo.getCardId(),
                cardInfo.getCardName(),
                benefit.getBenefitTitle(),
                category.getCategoryId()
        );


    }

}
