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
    // 지출총합, 카테고리이름, 카테고리ID
//    @GetMapping("api/v1/transactions/expenses/last-30-days")
//    List<MonthlySpendDto> getCategoriesByLast30Days(@RequestHeader("Authorization") String authorization);

    // 결제처, 금액, 카테고리
    @GetMapping("api/v1/transactions/list/last-30-days")
    List<PaymentDto> getPayments(@RequestHeader("Authorization") String authorization);

    // 금액, 카테고리
//    @GetMapping("api/v1/transactions/list/by-category/last-30-days")
//     List<PaymentsDto> getPayments(@RequestHeader("Authorization") String authorization);

    // 결제처, 금액
//    @GetMapping("api/v1/transactions/payments/last-30-days")
//    List<MonthlyPaymentDto> getLast30Days(@RequestHeader("Authorization") String authorization);
}