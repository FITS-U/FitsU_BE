package com.example.card.service;

import com.example.card.repository.CardRepository;
import com.example.card.response.CardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    public final CardRepository cardRepository;

    @Override
    public CardResponse getCardDetails(Long cardId) {
        return cardRepository.findCardDetails(cardId);
    }
}
