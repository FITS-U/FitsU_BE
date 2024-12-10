package com.example.card.service;

import com.example.card.domain.Category;
import com.example.card.repository.CardRepository;
import com.example.card.repository.CategoryRepository;
import com.example.card.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    public final CardRepository cardRepository;
    public final CategoryRepository categoryRepository;

    @Override
    public CardResponse getCardDetails(Long cardId) {
        List<Object[]> results = cardRepository.findCardDetails(cardId);

        if (results.isEmpty()) {
            throw new RuntimeException("Card not found");
        }
        Object[] firstResult = results.get(0);
        Long id = (Long) firstResult[0];
        String name = (String) firstResult[1];
        String prevSales = (String) firstResult[2];
        String annualFee = (String) firstResult[3];
        String cardApplyUrl = (String) firstResult[4];

        List<BenefitResponse> benefits = results.stream().map(result -> BenefitResponse.builder()
                        .benefitTitle((String) result[5])
                        .description((String) result[6])
                        .categoryId((Long) result[7])
                        .categoryName((String) result[8])
                        .build())
                .toList();

        return CardResponse.builder()
                .cardId(id)
                .cardName(name)
                .prevSales(prevSales)
                .annualFee(annualFee)
                .cardApplyUrl(cardApplyUrl)
                .benefits(benefits)
                .build();

    }

    @Override
    public List<CardResponse> getUserCardDetails(Long cardId) {
        return null;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        List<CategoryResponse> list = allCategories.stream().map(CategoryResponse::from).toList();
        return list;
    }

    @Override
    public List<CardBenefitResponse> getAllCards() {
        List<CardBenefitResponse> allCards = cardRepository.findAllCards();
        return allCards;
    }
}
