package com.example.tosspay.controller;

import com.example.tosspay.dto.PaymentApprovalRequest;
import com.example.tosspay.dto.PaymentApprovalResponse;
import com.example.tosspay.entity.TossPaymentStatus;
import com.example.tosspay.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/payments")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/approve")
    public ResponseEntity<PaymentApprovalResponse> approvePayment(@RequestBody PaymentApprovalRequest request) {
        try {
            PaymentApprovalResponse response = paymentService.approvePayment(request.getTossPaymentKey(), request.getOrderId(), request.getAmount(), request.getAccountId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            PaymentApprovalResponse errResponse = new PaymentApprovalResponse(
                    TossPaymentStatus.FAILED,
                    null,
                    null,
                    request.getOrderId(),
                    null,
                    null,
                    request.getAmount(),
                    Collections.emptyMap()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResponse);
        }
    }
}
