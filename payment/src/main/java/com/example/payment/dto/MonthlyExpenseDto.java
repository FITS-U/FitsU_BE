package com.example.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyExpenseDto {
    private int year;
    private int month;
    private double totalExpense;
}
