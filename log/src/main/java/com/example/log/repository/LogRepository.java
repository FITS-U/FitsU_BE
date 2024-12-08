package com.example.log.repository;

import com.example.log.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByUserId(UUID userId);
}
