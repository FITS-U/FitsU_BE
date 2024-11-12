package com.example.card.service;

import com.example.card.domain.Benefit;
import com.example.card.domain.CardInfo;
import com.example.card.domain.CardPerform;
import com.example.card.repository.BenefitRepository;
import com.example.card.repository.CardRepository;
import com.example.card.repository.PerformRepository;
import com.example.card.response.CardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    public final CardRepository cardRepository;
    public final PerformRepository performRepository;
    public final BenefitRepository benefitRepository;

    @Override
    public List<CardResponse> getInfoByCardId(Long cardId) {
        List<CardInfo> cardInfoList= cardRepository.getCardInfoByCardId(cardId);
        List<CardResponse> list = cardInfoList.stream().map(cardInfo -> {
            CardPerform cardPerform = performRepository.findByCardInfo_CardId(cardInfo.getCardId());
            Benefit benefit = benefitRepository.findByCardInfo_CardId(cardInfo.getCardId());
            return CardResponse.from(cardInfo, benefit, cardPerform);
        }).toList();
        return list;
    }
}
