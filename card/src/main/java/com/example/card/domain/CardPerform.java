package com.example.card.domain;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table(name = "CARD_PERFORM")
public class CardPerform {
    private String prevSales;
    private String annualFee;

    @OneToOne
    @JoinColumn(name = "CARD_ID")
    @Id
    private CardInfo cardInfo;
}
