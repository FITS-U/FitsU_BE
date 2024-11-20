package com.example.consumption.repository;


import com.example.consumption.domain.UserAccount;
import com.example.consumption.response.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<UserAccount, Long> {
    List<UserAccount> findByUserId(UUID userId);
    UserAccount save(UserAccount userAccount);
    void deleteByIsLinkedFalseOrIsLinkedNull();
    @Query(value = "select ua " +
            "from UserAccount ua " +
            "where ua.isLinked = true " +
            "and ua.userId = :userId")
    List<UserAccount> findUserAccountByUserId(UUID userId);
}
