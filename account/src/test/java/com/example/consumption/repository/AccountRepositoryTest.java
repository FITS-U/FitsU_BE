package com.example.consumption.repository;

import com.example.consumption.domain.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;
    @Test
    void findByUserIdAndAccountId() {
        List<UserAccount> byUserIdAndAccountId = accountRepository.findByUserIdAndAccountId(
                UUID.fromString("9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d")
                , 2l);
        System.out.println(byUserIdAndAccountId);
    }
}