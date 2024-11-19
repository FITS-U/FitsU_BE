package com.example.card.repository;

import com.example.card.domain.CardInfo;
import com.example.card.response.CardResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardInfo, Long> {

    @Query("SELECT new com.example.card.response.CardResponse(ci.cardId, ci.cardName, ci.bankId, ci.benSummary, " +
            "cp.prevSales, cp.annualFee, bd.description, mc.mainCtgId, mc.mainCtgName, sc.subCtgId, sc.subCtgName) " +
            "FROM CardInfo ci " +
            "LEFT JOIN CardPerform cp ON ci.cardId = cp.cardInfo.cardId " +
            "LEFT JOIN BenefitDesc bd ON ci.cardId = bd.cardInfo.cardId " +
            "LEFT JOIN CardCategory cc ON ci.cardId = cc.cardInfo.cardId " +
            "LEFT JOIN MainCategory mc ON cc.mainCategory.mainCtgId = mc.mainCtgId " +
            "LEFT JOIN SubCategory sc ON mc.mainCtgId = sc.mainCategory.mainCtgId " +
            "WHERE ci.cardId = :cardId")
    CardResponse findCardDetails(@Param("cardId") Long cardId);
}
