package com.example.tosspay.service;

import com.example.tosspay.config.TossPayConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.example.tosspay.config.HttpHeaderUtil.createHeaders;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final TossPayConfig tossPayConfig;
    private final RestTemplate restTemplate;

    public void approvePayment(String tossPaymentKey, String orderId, Double totalAmount, Long accountId) {
        String url = tossPayConfig.getApiUrl() + "/v1/payments/confirm";

        Map<String, Object> request = new HashMap<>();
        request.put("paymentKey", tossPaymentKey);
        request.put("tossOrderId", orderId);
        request.put("amount", totalAmount);
        request.put("accountId", accountId);

        HttpHeaders headers = createHeaders(tossPayConfig.getTestSecretApiKey());
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("결제 승인 실패: " + response.getBody());
        }

        deductBalanceFromAccount(accountId, totalAmount, orderId);
    }

    private void deductBalanceFromAccount(Long accountId, Double totalAmount, String orderId) {
        String accountServiceUrl = "http://localhost:8087/api/v1/accounts/deduct-balance";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("accountId", accountId);
        requestBody.put("amount", totalAmount);
        requestBody.put("orderId", orderId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(accountServiceUrl, entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("잔액 차감 실패: " + response.getBody());
        }
    }
}
