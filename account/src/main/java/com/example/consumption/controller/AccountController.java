package com.example.consumption.controller;

import com.example.consumption.domain.UserAccount;
import com.example.consumption.request.AccountRequest;
import com.example.consumption.response.AccountResponse;
import com.example.consumption.response.BankResponse;
import com.example.consumption.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts/users/{userId}")
    public List<AccountResponse> getUserAccount(@PathVariable String userId) {
        return accountService.getUserAccount(UUID.fromString(userId));
    }

    @GetMapping("/banks")
    public List<BankResponse> getBanks() {
        return accountService.getAllBanks();
    }

    @GetMapping("/banks/{bankId}")
    public Optional<BankResponse> getBank(@PathVariable Long bankId) {
        return accountService.getBankByBankId(bankId);
    }

    @PostMapping("/accounts")
    public List<AccountResponse> createAccounts(@RequestBody AccountRequest accountRequest) {
        List<AccountResponse> accounts = accountService.createAccounts(accountRequest);
        return accounts;
    }

    @GetMapping("/accounts/linked/users/{userId}")
    public List<AccountResponse> getLinkedUserAccounts(@PathVariable String userId) {
        return accountService.getLinkedUserAccounts(UUID.fromString(userId));
    }

    @GetMapping("/accounts/unlinked/users/{userId}")
    public List<AccountResponse> getUnlinkedUserAccounts(@PathVariable String userId) {
        return accountService.getUnlinkedUserAccounts(UUID.fromString(userId));
    }

}
