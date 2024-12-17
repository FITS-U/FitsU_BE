package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySpendDto {
    private Long categoryId;
    private String categoryName;
    private double totalSpending;
}
