package com.example.card.controller;

import com.example.card.domain.CardInfo;
import com.example.card.response.CardResponse;
import com.example.card.response.CategoryResponse;
import com.example.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
@CrossOrigin("http://localhost:3000")
public class CardController {

    private final CardService cardService;

    @GetMapping("/{cardId}")
    public List<CardResponse> getCardDetails(@PathVariable Long cardId) {
        return cardService.getCardDetails(cardId);
    }

    @GetMapping("/categories")
    public List<CategoryResponse> getCategories() {
        return cardService.getAllCategories();
    }
}
