package com.example.bankImage.controller;

import com.example.bankImage.response.BankImageResponse;
import com.example.bankImage.service.BankImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bankImages")
@AllArgsConstructor
public class BankImageController {
    private final BankImageService bankImageService;

    @GetMapping("/{bankId}")
    public BankImageResponse getBankImage(@PathVariable Long bankId) {
        return bankImageService.getImageByBankId(bankId);
    }

}
