package com.example.card.domain;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table(name = "CARD_PERFORM")
public class CardPerform {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long performId;
    private String prevSales;
    private String annualFee;

    @OneToOne
    @JoinColumn(name = "CARD_ID")
    private CardInfo cardInfo;
}
