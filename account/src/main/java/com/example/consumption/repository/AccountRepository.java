package com.example.consumption.repository;


import com.example.consumption.domain.UserAccount;
import com.example.consumption.response.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<UserAccount, Long> {
    List<UserAccount> findByUserIdAndAccountId(UUID userId, Long accountId);

    Boolean existsByUserIdAndAccountIdAndIsLinkedTrue(UUID userId, Long accountId);
}
