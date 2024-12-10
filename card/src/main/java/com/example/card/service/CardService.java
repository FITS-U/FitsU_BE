package com.example.card.service;

import com.example.card.domain.CardInfo;
import com.example.card.response.CardBenefitResponse;
import com.example.card.response.CardResponse;
import com.example.card.response.CategoryResponse;
import com.example.card.response.ImageUrlResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    CardResponse getCardDetails(Long cardId);
    List<CardResponse> getUserCardDetails(Long cardId);
    List<CategoryResponse> getAllCategories();
    List<CardBenefitResponse> getAllCards();
    ImageUrlResponse getCardImage(Long cardId);
}
