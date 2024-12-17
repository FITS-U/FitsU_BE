package com.example.card.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CardResponse{
        private Long cardId;
        private String cardName;
        private String prevSales;
        private String annualFee;
        private String cardApplyUrl;
        private String imageUrl;
        private List<BenefitResponse> benefits;
    }

