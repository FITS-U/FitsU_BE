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
@Table(name = "BENEFIT")
public class Benefit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long benefitId;
    private String benefitTitle;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "CARD_ID")
    private CardInfo cardInfo;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
