package com.example.image.service;

import com.example.image.response.CardImageResponse;

public interface ImageService {
    CardImageResponse getCardImageByCardId(Long cardId);
}
