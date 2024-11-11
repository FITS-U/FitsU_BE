package com.example.consumption.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Table(name = "USER_ACCOUNT")
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String accountNum;
    private String accName;
    private Double balance;
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank;
}
