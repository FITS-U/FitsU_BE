package com.example.tosspay.service;

import com.example.tosspay.config.TossPayConfig;
import com.example.tosspay.dto.PaymentApprovalResponse;
import com.example.tosspay.entity.Payment;
import com.example.tosspay.entity.TossPaymentMethod;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.example.tosspay.config.HttpHeaderUtil.createHeaders;
import static com.example.tosspay.entity.TossPaymentStatus.PENDING;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final TossPayConfig tossPayConfig;
    private final RestTemplate restTemplate;

    public PaymentApprovalResponse approvePayment(String tossPaymentKey, String orderId, Double totalAmount, Long accountId) {
        LocalDateTime requestAt = LocalDateTime.now();

        String url = tossPayConfig.getApiUrl() + "/v1/payments/confirm";

        Map<String, Object> request = new HashMap<>();
        request.put("paymentKey", tossPaymentKey);
        request.put("orderId", orderId);
        request.put("amount", totalAmount);

        HttpHeaders headers = createHeaders(tossPayConfig.getTestSecretApiKey());
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        logger.info("결제 승인 요청: paymentKey={}, orderId={}, amount={}", tossPaymentKey, orderId, totalAmount);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("결제 승인 실패: " + response.getBody());
        }

        Map responseBody = response.getBody();
        String approvedAtString = (String) responseBody.get("approvedAt");
        LocalDateTime approvedAt = LocalDateTime.parse(approvedAtString);

        String methodString = (String) responseBody.get("method");
        TossPaymentMethod method = TossPaymentMethod.valueOf(methodString.toUpperCase());

        Map<String, Object> balanceResponse = deductBalanceFromAccount(accountId, totalAmount, orderId);

        Payment payment = new Payment();
        payment.setStatus(PENDING);
        payment.setTossPaymentKey(tossPaymentKey);
        payment.setMethod(method);
        payment.setOrderId(orderId);
        payment.setRequestedAt(requestAt);
        payment.setApprovedAt(approvedAt);
        payment.setTotalAmount(totalAmount);
        payment.setAccountId(accountId);

        logger.info("결제 승인 완료: paymentKey={}, orderId={}, approvedAt={}", tossPaymentKey, orderId, approvedAt);

        return PaymentApprovalResponse.from(payment,balanceResponse);
    }

    private Map<String, Object> deductBalanceFromAccount(Long accountId, Double totalAmount, String orderId) {
        String accountServiceUrl = "http://localhost:8087/api/v1/accounts/deduct-balance";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("accountId", accountId);
        requestBody.put("amount", totalAmount);
        requestBody.put("orderId", orderId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        logger.info("잔액 차감 요청: accountId={}, amount={}, orderId={}", accountId, totalAmount, orderId);

        ResponseEntity<Map> response = restTemplate.postForEntity(accountServiceUrl, entity, Map.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            logger.error("잔액 차감 실패: {}", response.getBody());
            throw new RuntimeException("잔액 차감 실패: " + response.getBody());
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();

        logger.info("잔액 차감 완료: accountId={}, amount={}", accountId, totalAmount);

        return responseBody;
    }
}
