package com.example.consumption.repository;


import com.example.consumption.domain.Bank;
import com.example.consumption.domain.UserAccount;
import com.example.consumption.response.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<UserAccount, Long> {
    List<UserAccount> findByUserId(UUID userId);
    UserAccount save(UserAccount userAccount);
    @Query(value = "select ua " +
            "from UserAccount ua " +
            "where ua.isLinked = true " +
            "and ua.userId = :userId")
    List<UserAccount> findUserAccountByUserId(UUID userId);

    @Query(value = "select ua " +
            "from UserAccount ua " +
            "where ua.isLinked = false " +
            "and ua.userId = :userId " +
            "and ua.bank.bankId IN :bankIds")
    List<UserAccount> findUnLinkedUserAccountByUserId(UUID userId, List<Long> bankIds);

    @Query(value = "select ua " +
            "from UserAccount ua " +
            "where ua.userId = :userId " +
            "and ua.bank.bankId = :bankId")
    List<UserAccount> findByUserIdAndBankId(UUID userId, Long bankId);
}
