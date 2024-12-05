package com.example.consumption.service;

import com.example.consumption.domain.Bank;
import com.example.consumption.domain.UserAccount;
import com.example.consumption.request.AccountRequest;
import com.example.consumption.request.BalanceRequest;
import com.example.consumption.response.AccountResponse;
import com.example.consumption.response.BalanceResponse;
import com.example.consumption.response.BankResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface AccountService {
    List<AccountResponse> getUserAccount(UUID userId);
    List<BankResponse> getAllBanks();
    Optional<BankResponse> getBankByBankId(Long bankId);
    List<AccountResponse> getLinkedUserAccounts(UUID userId);
    List<AccountResponse> createAccounts(AccountRequest request, UUID userId);
    List<AccountResponse> getUnlinkedUserAccounts(UUID userId, List<Long> bankIds);
    BalanceResponse deductBalance(UUID userId ,BalanceRequest request);
}
