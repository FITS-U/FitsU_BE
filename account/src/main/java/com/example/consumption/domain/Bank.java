package com.example.consumption.domain;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table(name = "BANK")
public class Bank {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;
    private String bankName;
}
