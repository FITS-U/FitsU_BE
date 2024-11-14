package com.example.consumption.controller;

import com.example.consumption.response.AccountResponse;
import com.example.consumption.response.BankResponse;
import com.example.consumption.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts/{userId}/{accountId}")
    public List<AccountResponse> getUserAccount(@PathVariable UUID userId, @PathVariable Long accountId) {
        return accountService.getUserAccount(userId, accountId);
    }

    @GetMapping("/banks")
    public List<BankResponse> getBanks() {
        return accountService.getAllBanks();
    }
}
