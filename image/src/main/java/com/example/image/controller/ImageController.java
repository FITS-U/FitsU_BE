package com.example.image.controller;

import com.example.image.response.CardImageResponse;
import com.example.image.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cardImages")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{cardId}")
    public CardImageResponse getCardImageByCardId(@PathVariable("cardId") Long cardId) {
        return imageService.getCardImageByCardId(cardId);
    }

}
