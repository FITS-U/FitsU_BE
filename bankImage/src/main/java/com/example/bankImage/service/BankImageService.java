package com.example.bankImage.service;

import com.example.bankImage.response.BankImageResponse;
import org.springframework.stereotype.Service;

@Service
public interface BankImageService {
    BankImageResponse getImageByBankId(Long bankId);
}
