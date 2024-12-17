package com.example.model.client;


import com.example.model.dto.MonthlyPaymentDto;
import com.example.model.dto.PaymentDto;
import com.example.model.dto.PaymentsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "transaction-service", url = "http://localhost:8084")
public interface TransactionClient {
    @GetMapping("api/v1/transactions/list/last-30-days")
    List<PaymentDto> getPayments(@RequestHeader("Authorization") String authorization);
}