package com.example.card.response;

import com.example.card.domain.*;
import lombok.Builder;

@Builder
public record CardResponse(
        Long cardId,
        String cardName,
        Long bankId,
        String benSummary,
        String prevSales,
        String annualFee,
        String description,
        Long mainCtgId,
        String mainCtgName,
        Long subCtgId,
        String subCtgName
        ) {

    public static CardResponse from(
            CardInfo cardInfo,
            CardPerform cardPerform,
            BenefitDesc benefitDesc,
            MainCategory mainCategory,
            SubCategory subCategory
            ) {
        return new CardResponse(
                cardInfo.getCardId(),
                cardInfo.getCardName(),
                cardInfo.getBankId(),
                cardInfo.getBenSummary(),
                cardPerform.getPrevSales(),
                cardPerform.getAnnualFee(),
                benefitDesc.getDescription(),
                mainCategory.getMainCtgId(),
                mainCategory.getMainCtgName(),
                subCategory.getSubCtgId(),
                subCategory.getSubCtgName());

    }

}
