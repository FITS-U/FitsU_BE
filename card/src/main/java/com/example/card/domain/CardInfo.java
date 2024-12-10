package com.example.card.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "card_info")
public class CardInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private String cardName;
    private String prevSales;
    private String annualFee;
    @Column(length = 1000)
    private String cardApplyUrl;
    @Column(length = 1000)
    private String imageUrl;
}
