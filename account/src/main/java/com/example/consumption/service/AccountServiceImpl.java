package com.example.consumption.service;

import com.example.consumption.domain.UserAccount;
import com.example.consumption.repository.AccountRepository;
import com.example.consumption.response.AccountResponse;

import java.util.List;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public List<AccountResponse> getUserAccount(UUID userId, Long accountId) {
        List<UserAccount> accounts = accountRepository.getUserAccountByUserId(userId, accountId);
        List<AccountResponse> list = accounts.stream().map(AccountResponse::from).toList();
        return list;
    }
}
