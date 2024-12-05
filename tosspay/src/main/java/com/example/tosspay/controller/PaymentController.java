package com.example.tosspay.controller;

import com.example.tosspay.dto.PaymentApprovalRequest;
import com.example.tosspay.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/approve")
    public ResponseEntity<String> approvePayment(@RequestBody PaymentApprovalRequest request){
        try {
            paymentService.approvePayment(request.getTossPaymentKey(), request.getOrderId(), request.getAmount(), request.getAccountId(), request.getMethod());
            return ResponseEntity.ok("결제 승인 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결제 승인 실패: " + e.getMessage());
        }
    }

}
