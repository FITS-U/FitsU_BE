package com.example.image.service;

import com.example.image.domain.CardImage;
import com.example.image.repository.CardImageRepository;
import com.example.image.response.CardImageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor

public class ImageServiceImpl implements ImageService {
    private final CardImageRepository cardImageRepository;

    @Override
    public CardImageResponse getCardImageByCardId(Long cardId) {
        CardImage image = cardImageRepository.findByCardId(cardId);
        return CardImageResponse.from(image);
    }
}
