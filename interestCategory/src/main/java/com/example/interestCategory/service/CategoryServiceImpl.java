package com.example.interestCategory.service;

import com.example.interestCategory.domain.CategoryOfInterest;
import com.example.interestCategory.dto.CategoryResponse;
import com.example.interestCategory.dto.MainCtgRequest;
import com.example.interestCategory.global.CustomUserDetails;
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
    public void saveCategories(CustomUserDetails user, List<Long> categoryIds) {
        if(categoryIds.size() != 5) {
            throw new IllegalArgumentException("다섯 개의 카테고리를 선택해주세요");
        }
        for(Long categoryId : categoryIds) {
            CategoryOfInterest categoryOfInterest = new CategoryOfInterest(null, categoryId, user.getCategoryOfInterest().getUserId());
            categoryRepository.save(categoryOfInterest);
        }
    }

//    @Override
//    public void saveCategories(String phoneNum ,List<Long> categoryIds) {
//        if(categoryIds.size() != 5) {
//            throw new IllegalArgumentException("다섯 개의 카테고리를 선택해주세요");
//        }
//
//        for(Long categoryId : categoryIds) {
//            CategoryOfInterest categoryOfInterest = new CategoryOfInterest(null, categoryId, userId);
//            categoryRepository.save(categoryOfInterest);
//        }
//    }
}
