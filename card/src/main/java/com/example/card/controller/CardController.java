package com.example.card.controller;

import com.example.card.response.CardResponse;
import com.example.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/cards/{cardId}")
    public List<CardResponse> getInfoByCardId(@PathVariable Long cardId) {
        return cardService.getInfoByCardId(cardId);
    }
}
