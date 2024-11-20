package com.example.consumption.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "USER_ACCOUNT")
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String accountNum;
    private String accName;
    private Double balance;
    private UUID userId;
    private Boolean isLinked;

    @ManyToOne
    @JoinColumn(name = "BANK_ID", nullable = false)
    private Bank bank;
}
