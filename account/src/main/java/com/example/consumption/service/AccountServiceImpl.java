package com.example.consumption.service;

import com.example.consumption.domain.Bank;
import com.example.consumption.domain.UserAccount;
import com.example.consumption.repository.AccountRepository;
import com.example.consumption.repository.BankRepository;
import com.example.consumption.response.AccountResponse;
import com.example.consumption.response.BankResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

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
    public List<AccountResponse> createAccounts(List<UserAccount> userAccounts) {
        List<AccountResponse> account = new ArrayList<>();

        for(UserAccount userAccount : userAccounts) {
            if(Boolean.TRUE.equals(userAccount.getIsLinked())) {
                continue;
            }
            UserAccount newAccount = new UserAccount(
                    userAccount.getAccountId(),
                    userAccount.getAccountNum(),
                    userAccount.getAccName(),
                    userAccount.getBalance(),
                    userAccount.getUserId(),
                    true,
                    userAccount.getBank()
            );
            UserAccount saveAccount = accountRepository.save(newAccount);

            if(saveAccount != null) {
                accountRepository.save(saveAccount);
                accountRepository.deleteByIsLinkedFalseOrIsLinkedNull();
                account.add(AccountResponse.from(saveAccount));
            }
        }
        return account;
    }

    @Override
    public List<AccountResponse> getLinkedUserAccounts(UUID userId) {
        List<UserAccount> userAccounts = accountRepository.findUserAccountByUserId(userId);
        return userAccounts.stream().map(AccountResponse::from).toList();
    }
}
