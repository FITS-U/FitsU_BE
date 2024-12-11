package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyPaymentDto {
    private String recipient;
    private Double price;
}
