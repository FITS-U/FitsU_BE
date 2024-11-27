package com.example.interestCategory.service;

import com.example.interestCategory.dto.CategoryResponse;
import com.example.interestCategory.global.CustomUserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryService {
    List<CategoryResponse> getCategoriesByUserId(UUID userId);
    void saveCategories(UUID userId, List<Long> categoryIds);
}
