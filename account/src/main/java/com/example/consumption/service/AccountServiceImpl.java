package com.example.consumption.service;

import com.example.consumption.domain.Bank;
import com.example.consumption.domain.UserAccount;
import com.example.consumption.repository.AccountRepository;
import com.example.consumption.repository.BankRepository;
import com.example.consumption.request.AccountRequest;
import com.example.consumption.request.BalanceRequest;
import com.example.consumption.response.AccountResponse;
import com.example.consumption.response.BalanceResponse;
import com.example.consumption.response.BankResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    @Override
    public List<AccountResponse> getUserAccount(UUID userId) {
        List<UserAccount> accounts = accountRepository.findByUserId(userId);
        List<AccountResponse> list = accounts.stream().map(AccountResponse::from).toList();
        return list;
    }

    @Override
    public List<BankResponse> getAllBanks() {
        List<Bank> banks = bankRepository.findAll();
        List<BankResponse> list = banks.stream().map(BankResponse::from).toList();
        return list;
    }

    @Override
    public Optional<BankResponse> getBankByBankId(Long bankId) {
        Optional<Bank> bank = bankRepository.findById(bankId);
        return bank.map(BankResponse::from);
    }


    @Override
    public List<AccountResponse> createAccounts(AccountRequest request, UUID userId) {
        List<AccountResponse> accountResponses = new ArrayList<>();

        for (Long bankId : request.getBankIds()){

            List<UserAccount> accounts = accountRepository.findByUserIdAndBankId(userId, bankId);

            for (UserAccount account : accounts) {
                if (Boolean.TRUE.equals(account.getIsLinked())) {
                    continue;
                }

                account.setIsLinked(true);
                UserAccount saveAccount = accountRepository.save(account);
                accountResponses.add(AccountResponse.from(saveAccount));
            }

        }
        return accountResponses;
    }

    @Override
    public List<AccountResponse> getLinkedUserAccounts(UUID userId) {
        List<UserAccount> userAccounts = accountRepository.findUserAccountByUserId(userId);
        if (userAccounts == null || userAccounts.isEmpty()) {
            return Collections.emptyList();
        }
        return userAccounts.stream().map(AccountResponse::from).toList();
    }

    @Override
    public List<AccountResponse> getUnlinkedUserAccounts(UUID userId, List<Long> bankIds) {
        List<UserAccount> unlinkedAccounts = accountRepository.findUnLinkedUserAccountByUserId(userId, bankIds);
        if (unlinkedAccounts == null || unlinkedAccounts.isEmpty()) {
            return Collections.emptyList();
        }
        return unlinkedAccounts.stream().map(AccountResponse::from).toList();
    }

    @Override
    public BalanceResponse deductBalance(UUID userId, BalanceRequest request) {
        UserAccount userAccount = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found for ID: " + request.getAccountId()));

        if(userAccount.getBalance() < request.getAmount()) {
            throw new IllegalArgumentException("Not enough balance");
        }

        Bank bank = userAccount.getBank();

        userAccount.setBalance(userAccount.getBalance() - request.getAmount());
        accountRepository.save(userAccount);

        return BalanceResponse.from(userAccount, bank);

    }
}
