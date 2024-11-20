package com.example.consumption.controller;

import com.example.consumption.response.AccountResponse;
import com.example.consumption.response.BankResponse;
import com.example.consumption.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:3000")
public class AccountController {

    private final AccountService accountService;

    // 유저의 모든 계좌 정보 조회
    @GetMapping("/accounts/users/{userId}")
    public List<AccountResponse> getUserAccount(@PathVariable UUID userId, @PathVariable Long accountId) {
        return accountService.getUserAccount(userId, accountId);
    }

    @GetMapping("/banks")
    public List<BankResponse> getAllBanks() {
        return accountService.getAllBanks();
    }

}
