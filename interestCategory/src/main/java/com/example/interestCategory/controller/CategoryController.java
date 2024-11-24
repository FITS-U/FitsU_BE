package com.example.interestCategory.controller;

import com.example.interestCategory.dto.CategoryResponse;
import com.example.interestCategory.dto.MainCtgRequest;
import com.example.interestCategory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/interest-category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/users/{userId}")
    public List<CategoryResponse> getCategoryByUserId(@PathVariable String userId) {
        List<CategoryResponse> categories = categoryService.getCategoriesByUserId(UUID.fromString(userId));
        return categories;
    }

    @PostMapping
    public ResponseEntity<String> saveCategories(@RequestBody MainCtgRequest mainCtgRequest) {
        UUID userId = mainCtgRequest.getUserId();
        List<Long> mainCtgIds = mainCtgRequest.getMainCtgIds();
        categoryService.saveCategories(userId, mainCtgIds);
        return ResponseEntity.ok("카테고리가 저장되었습니다.");
    }
}
