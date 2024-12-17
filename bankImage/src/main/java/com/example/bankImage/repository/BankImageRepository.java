package com.example.bankImage.repository;

import com.example.bankImage.domain.BankImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankImageRepository extends MongoRepository<BankImage, String> {
    BankImage findByBankId(Long bankId);
}
