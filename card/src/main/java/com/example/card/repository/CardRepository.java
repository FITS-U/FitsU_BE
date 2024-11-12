package com.example.card.repository;

import com.example.card.domain.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CardRepository extends JpaRepository<CardInfo, Long> {
    List<CardInfo> getCardInfoByCardId(Long cardId);
}
