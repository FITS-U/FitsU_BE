package com.example.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecommendResponse {
    private String repBenefits;
    private Long cardId;
    private String cardName;
    private String details;
}
