package com.example.consumption.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BalanceRequest {
    private Long accountId;
    private Double amount;
    private String orderId;
}
