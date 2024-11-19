package com.example.card.controller;

import com.example.card.domain.CardInfo;
import com.example.card.response.CardResponse;
import com.example.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CardController {

    private final CardService cardService;

    @GetMapping("/cards/{cardId}")
    public CardResponse getCardDetails(@PathVariable Long cardId) {
        return cardService.getCardDetails(cardId);
    }
}
