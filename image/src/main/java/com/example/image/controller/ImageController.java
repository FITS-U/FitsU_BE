package com.example.image.controller;

import com.example.image.domain.CardImage;
import com.example.image.response.CardImageResponse;
import com.example.image.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cardImages")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{cardId}")
    public CardImageResponse getCardImageByCardId(@PathVariable Long cardId) {
        return imageService.getCardImageByCardId(cardId);
    }

}
