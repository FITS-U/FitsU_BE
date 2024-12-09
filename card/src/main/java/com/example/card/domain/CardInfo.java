package com.example.card.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "card_info")
public class CardInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private String cardName;
    private String prevSales;
    private String annualFee;
    private String cardApplyUrl;
}
