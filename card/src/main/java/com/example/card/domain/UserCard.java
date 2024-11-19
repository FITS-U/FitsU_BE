package com.example.card.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCardId;
    private LocalDate expDate;
    private Long accountId;

    @OneToOne
    @JoinColumn(name = "CARD_ID")
    private CardInfo cardInfo;
}
