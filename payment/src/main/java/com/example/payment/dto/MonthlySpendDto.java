package com.example.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySpendDto{
    private Long mainCtgId;
    private double totalSpending;

    public static MonthlySpendDto from(Object[] result) {
        return new MonthlySpendDto(
                ((Number) result[0]).longValue(),
                ((Number) result[1]).doubleValue()
        );
    }
}


