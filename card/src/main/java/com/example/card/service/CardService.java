package com.example.card.service;

import com.example.card.domain.CardInfo;
import com.example.card.response.CardResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    List<CardResponse> getInfoByCardId(Long cardId);
}
