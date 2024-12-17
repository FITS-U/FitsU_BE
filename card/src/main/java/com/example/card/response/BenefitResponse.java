package com.example.card.response;

import com.example.card.domain.Benefit;
import com.example.card.domain.Category;
import lombok.Builder;

@Builder
public record BenefitResponse(
        String benefitTitle,
        String description,
        Long categoryId,
        String categoryName
) {

    public static BenefitResponse from(
            Benefit benefit,
            Category category
    ) {
        return new BenefitResponse(
                benefit.getBenefitTitle(),
                benefit.getDescription(),
                category.getCategoryId(),
                category.getCategoryName()
        );


    }
}
