package com.example.card.repository;

import com.example.card.domain.CardPerform;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PerformRepository extends JpaRepository<CardPerform, Long> {
    CardPerform findByCardInfo_CardId(Long cardId);
}
