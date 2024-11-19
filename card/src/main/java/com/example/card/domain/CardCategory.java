package com.example.card.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkId;

    @ManyToOne
    @JoinColumn(name = "CARD_ID")
    private CardInfo cardInfo;

    @ManyToOne
    @JoinColumn(name = "MAIN_CTG_ID")
    private MainCategory mainCategory;
}
