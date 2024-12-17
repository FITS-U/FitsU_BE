package com.example.payment.repository;

import com.example.payment.domain.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TransactionRepositoryTest {
@Autowired
private TransactionRepository paymentRepository;
    @Test
    void findByUserIdAndCreatedAtBetween() {
        UUID uuid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < 5; i++) {
            paymentRepository.save(
                    new Payment(null, uuid, null, null, now.minusDays(i/2), null, null, null));
        }
        List<Payment> byUserIdAndCreatedAtBetween = paymentRepository.findByUserIdAndCreatedAtBetween(uuid, now.minusDays(1), now);
        assertEquals(2, byUserIdAndCreatedAtBetween.size());
    }
}