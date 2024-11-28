package com.example.consumption.controller;

import com.example.consumption.domain.UserAccount;
import com.example.consumption.global.JwtUtils;
import com.example.consumption.request.AccountRequest;
import com.example.consumption.response.AccountResponse;
import com.example.consumption.response.BankResponse;
import com.example.consumption.service.AccountService;
import com.example.consumption.service.AuthService;
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
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AccountController {

    private final AccountService accountService;
    private final AuthService authService;

    @GetMapping("/accounts")
    public List<AccountResponse> getUserAccount(@RequestHeader("Authorization") String authorization) {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);

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
    public List<AccountResponse> createAccounts(@RequestBody AccountRequest accountRequest, @RequestHeader("Authorization") String authorization) throws AccessDeniedException {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);

        List<AccountResponse> accounts = accountService.createAccounts(accountRequest, UUID.fromString(userId));
        return accounts;
    }

    @GetMapping("/accounts/linked")
    public List<AccountResponse> getLinkedUserAccounts(@RequestHeader("Authorization") String authorization) {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);

        return accountService.getLinkedUserAccounts(UUID.fromString(userId));
    }

    @GetMapping("/accounts/unlinked")
    public List<AccountResponse> getUnlinkedUserAccounts(@RequestParam List<Long> bankIds, @RequestHeader("Authorization") String authorization)  {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);

        return accountService.getUnlinkedUserAccounts(UUID.fromString(userId), bankIds);
    }

}
