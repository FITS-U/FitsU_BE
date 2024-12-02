package com.example.tosspay.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Long orderId;
    private String tossOrderId;
    private String tossPaymentKey;

    @Enumerated(EnumType.STRING)
    private TossPaymentMethod tossPaymentMethod;
    @Enumerated(EnumType.STRING)
    private TossPaymentStatus tossPaymentStatus;

    private Double totalAmount;
    private LocalDateTime approvedAt;
    private LocalDateTime requestedAt;


}
