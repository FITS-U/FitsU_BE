package com.example.card.service;

import com.example.card.domain.CardInfo;
import com.example.card.repository.CardRepository;
import com.example.card.response.CardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    public final CardRepository cardRepository;

    @Override
    public List<CardResponse> getInfoByCardId(Long cardId) {
        List<CardInfo> cardInfo = cardRepository.getCardInfoByCardId(cardId);
        List<CardResponse> list = cardInfo.stream().map(CardResponse::from).toList();
        return list;
    }
}
