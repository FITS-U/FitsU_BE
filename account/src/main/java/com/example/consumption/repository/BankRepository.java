package com.example.consumption.repository;

import com.example.consumption.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Long> {
    List<Bank> findByBankId(Long bankId);
}
