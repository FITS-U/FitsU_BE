package com.example.card.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "BENEFIT")
public class Benefit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long benefitId;
    private String description;
    private Long categoryId;
    private Double discountRate;

    @OneToOne
    @JoinColumn(name = "CARD_ID")
    private CardInfo cardInfo;
}
