package com.example.interestCategory.service;

import com.example.interestCategory.domain.CategoryOfInterest;
import com.example.interestCategory.dto.CategoryResponse;
import com.example.interestCategory.dto.MainCtgRequest;
import com.example.interestCategory.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getCategoriesByUserId(UUID userId) {
        List<CategoryOfInterest> categories = categoryRepository.findByUserId(userId);
        List<CategoryResponse> list = categories.stream().map(CategoryResponse::from).toList();
        return list;
    }

    @Override
    public void saveCategories(UUID userId ,List<Long> mainCtgIds) {
        if(mainCtgIds.size() != 3) {
            throw new IllegalArgumentException("세 개의 카테고리를 선택해주세요");
        }

        for(Long mainCtgId : mainCtgIds) {
            CategoryOfInterest categoryOfInterest = new CategoryOfInterest(null, mainCtgId, userId);
            categoryRepository.save(categoryOfInterest);
        }
    }
}
