package com.example.image.repository;

import com.example.image.domain.CardImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("cardImageRepository")
public interface CardImageRepository extends MongoRepository<CardImage, String> {
    CardImage findByCardId(Long cardId);
}
