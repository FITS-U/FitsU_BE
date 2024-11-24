package com.example.consumption.controller;

import com.example.consumption.domain.UserAccount;
import com.example.consumption.request.AccountRequest;
import com.example.consumption.response.AccountResponse;
import com.example.consumption.response.BankResponse;
import com.example.consumption.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts/users/{userId}")
    public List<AccountResponse> getUserAccount(@PathVariable String userId) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();

        if(!currentUserId.equals(userId)) {
            throw new AccessDeniedException("본인의 계좌만 조회할 수 있습니다.");
        }

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
    public List<AccountResponse> getUnlinkedUserAccounts(@PathVariable String userId, @RequestParam Long bankId) {
        return accountService.getUnlinkedUserAccounts(UUID.fromString(userId), bankId);
    }

}
