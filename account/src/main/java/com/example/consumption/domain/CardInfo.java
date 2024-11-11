package com.example.consumption.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "CARD_INFO")
public class CardInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private String cardName;

    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank;
}
