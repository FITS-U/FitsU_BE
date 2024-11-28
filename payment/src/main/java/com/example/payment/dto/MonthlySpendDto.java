package com.example.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySpendDto{
    private Long categoryId;
    private String categoryName;
    private double totalSpending;

    public static MonthlySpendDto from(Object[] result) {
        return new MonthlySpendDto(
                ((Number) result[0]).longValue(),
                result[1].toString(),
                ((Number) result[2]).doubleValue()
        );
    }
}


