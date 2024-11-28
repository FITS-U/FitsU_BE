package com.example.card.repository;

import com.example.card.domain.CardInfo;
import com.example.card.domain.Category;
import com.example.card.response.CardResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardInfo, Long> {

    @Query("SELECT new com.example.card.response.CardResponse(ci.cardId, ci.cardName, ci.bankId, " +
            "cp.prevSales, cp.annualFee, b.benefitTitle, b.description, c.categoryId, c.categoryName) " +
            "FROM CardInfo ci " +
            "LEFT JOIN CardPerform cp ON ci.cardId = cp.cardInfo.cardId " +
            "LEFT JOIN Benefit b ON ci.cardId = b.cardInfo.cardId " +
            "RIGHT join Category c ON c.categoryId = b.category.categoryId " +
            "WHERE ci.cardId = :cardId")
    List<CardResponse> findCardDetails(@Param("cardId") Long cardId);
}
