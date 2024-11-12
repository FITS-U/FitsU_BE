package com.example.consumption.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BANK")
public class Bank {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;
    private String bankName;
}
