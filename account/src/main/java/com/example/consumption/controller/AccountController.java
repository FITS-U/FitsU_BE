package com.example.consumption.controller;

import com.example.consumption.domain.UserAccount;
import com.example.consumption.global.JwtUtils;
import com.example.consumption.request.AccountRequest;
import com.example.consumption.response.AccountResponse;
import com.example.consumption.response.BankResponse;
import com.example.consumption.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final JwtUtils jwtUtils;

    @GetMapping("/accounts")
    public List<AccountResponse> getUserAccount(@RequestHeader("Authorization") String authorization) throws AccessDeniedException {

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        return accountService.getUserAccount(UUID.fromString(currentUserId));
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
    public List<AccountResponse> createAccounts(@RequestBody AccountRequest accountRequest, @RequestHeader("Authorization") String authorization) throws AccessDeniedException {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        if(!accountRequest.getUserId().equals(UUID.fromString(currentUserId))) {
            throw new AccessDeniedException("계좌를 생성할 수 없습니다.");
        }
        List<AccountResponse> accounts = accountService.createAccounts(accountRequest);
        return accounts;
    }

    @GetMapping("/accounts/linked")
    public List<AccountResponse> getLinkedUserAccounts(@RequestHeader("Authorization") String authorization) throws AccessDeniedException {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }
        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        return accountService.getLinkedUserAccounts(UUID.fromString(currentUserId));
    }

    @GetMapping("/accounts/unlinked")
    public List<AccountResponse> getUnlinkedUserAccounts(@RequestParam Long bankId, @RequestHeader("Authorization") String authorization) throws AccessDeniedException {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }
        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        return accountService.getUnlinkedUserAccounts(UUID.fromString(currentUserId), bankId);
    }

}