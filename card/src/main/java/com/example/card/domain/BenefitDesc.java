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
@Table(name = "BENEFIT_DESC")
public class BenefitDesc {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long descId;
    private String description;

    @ManyToOne
    @JoinColumn(name = "CARD_ID")
    private CardInfo cardInfo;

    @ManyToOne
    @JoinColumn(name = "SUB_CTG_ID")
    private SubCategory subCategory;
}
