package com.example.interestCategory.repository;

import com.example.interestCategory.domain.CategoryOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryOfInterest, Long> {
    List<CategoryOfInterest> findByUserId(UUID userId);
}
