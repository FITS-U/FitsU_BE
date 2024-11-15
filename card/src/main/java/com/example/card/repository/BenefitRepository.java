package com.example.card.repository;

import com.example.card.domain.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitRepository extends JpaRepository<Benefit, Long> {
    Benefit findByCardInfo_CardId(Long cardId);
}
