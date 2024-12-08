package com.example.bankImage.controller;

import com.example.bankImage.response.BankImageResponse;
import com.example.bankImage.service.BankImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bankImages")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class BankImageController {
    private final BankImageService bankImageService;

    @GetMapping("/{bankId}")
    public BankImageResponse getBankImage(@PathVariable Long bankId) {
        return bankImageService.getImageByBankId(bankId);
    }

}
