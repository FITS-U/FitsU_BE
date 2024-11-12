package com.example.consumption.service;

import com.example.consumption.domain.Bank;
import com.example.consumption.response.AccountResponse;
import com.example.consumption.response.BankResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AccountService {
    List<AccountResponse> getUserAccount(UUID userId, Long accountId);
    List<BankResponse> getAllBanks();
}
