package com.example.card.response;

import com.example.card.domain.*;
import lombok.Builder;

@Builder
public record CardResponse(
        Long cardId,
        String cardName,
        Long bankId,
        String prevSales,
        String annualFee,
        String benefitTitle,
        String description,
        Long mainCtgId,
        String mainCtgName
        ) {

    public static CardResponse from(
            CardInfo cardInfo,
            CardPerform cardPerform,
            Benefit benefit,
            MainCategory mainCategory
            ) {
        return new CardResponse(
                cardInfo.getCardId(),
                cardInfo.getCardName(),
                cardInfo.getBankId(),
                cardPerform.getPrevSales(),
                cardPerform.getAnnualFee(),
                benefit.getBenefitTitle(),
                benefit.getDescription(),
                mainCategory.getMainCtgId(),
                mainCategory.getMainCtgName()
        );


    }

}
