package com.example.card.controller;

import com.example.card.domain.CardInfo;
import com.example.card.response.CardBenefitResponse;
import com.example.card.response.CardResponse;
import com.example.card.response.CategoryResponse;
import com.example.card.service.CardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class CardController {

    private final CardService cardService;

    @GetMapping(value ="/{cardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardResponse> getCardDetails(@PathVariable Long cardId) {
        CardResponse cardResponse = cardService.getCardDetails(cardId);
        return ResponseEntity.ok(cardResponse);
    }

    @GetMapping
    public List<CardBenefitResponse> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/categories")
    public List<CategoryResponse> getCategories() {
        return cardService.getAllCategories();
    }
}
