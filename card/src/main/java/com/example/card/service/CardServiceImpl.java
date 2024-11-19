package com.example.card.service;

import com.example.card.domain.CardInfo;
import com.example.card.repository.CardRepository;
import com.example.card.response.CardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    public final CardRepository cardRepository;

    @Override
    public List<CardResponse> getCardDetails(Long cardId) {
        return cardRepository.findCardDetails(cardId);
    }

    @Override
    public List<CardResponse> getUserCardDetails(Long cardId) {
        return null;
    }
}
