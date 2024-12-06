package com.example.model.client;


import com.example.model.dto.MonthlySpendDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "transaction-service", url = "http://localhost:8084")
public interface TransactionClient {
    @GetMapping("api/v1/transactions/expenses/last-30-days")
    List<MonthlySpendDto> getSumOfLast30Days(@RequestHeader("Authorization") String authorization);
}