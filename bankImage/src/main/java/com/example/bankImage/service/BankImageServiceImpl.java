package com.example.bankImage.service;

import com.example.bankImage.domain.BankImage;
import com.example.bankImage.repository.BankImageRepository;
import com.example.bankImage.response.BankImageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankImageServiceImpl implements BankImageService {
    private final BankImageRepository bankImageRepository;

    @Override
    public BankImageResponse getImageByBankId(Long bankId) {
        BankImage image = bankImageRepository.findByBankId(bankId);
        return BankImageResponse.from(image);
    }
}
