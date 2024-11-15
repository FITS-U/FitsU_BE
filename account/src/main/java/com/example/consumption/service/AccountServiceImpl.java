package com.example.consumption.service;

import com.example.consumption.domain.Bank;
import com.example.consumption.domain.UserAccount;
import com.example.consumption.repository.AccountRepository;
import com.example.consumption.repository.BankRepository;
import com.example.consumption.response.AccountResponse;
import com.example.consumption.response.BankResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    @Override
    public List<AccountResponse> getUserAccount(UUID userId, Long accountId) {
        List<UserAccount> accounts = accountRepository.findByUserIdAndAccountId(userId, accountId);
        List<AccountResponse> list = accounts.stream().map(AccountResponse::from).toList();
        return list;
    }

    @Override
    public List<BankResponse> getAllBanks() {
        List<Bank> banks = bankRepository.findAll();
        List<BankResponse> list = banks.stream().map(BankResponse::from).toList();
        return list;
    }
}
