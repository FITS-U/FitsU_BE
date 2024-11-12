package com.example.card.domain;

import jakarta.persistence.*;
import lombok.Getter;

import javax.smartcardio.Card;

@Entity
@Getter
@Table(name = "BENEFIT")
public class Benefit {
    private String description;
    private String category;
    private Double discountRate;

    @OneToOne
    @JoinColumn(name = "CARD_ID")
    @Id
    private CardInfo cardInfo;
}
