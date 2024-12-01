package com.example.interestCategory.controller;

import com.example.interestCategory.dto.CategoryResponse;
import com.example.interestCategory.dto.MainCtgRequest;
import com.example.interestCategory.global.CustomUserDetails;
import com.example.interestCategory.global.JwtUtils;
import com.example.interestCategory.service.AuthService;
import com.example.interestCategory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/interest-category")
@RequiredArgsConstructor
@CrossOrigin(
        methods = {
                RequestMethod.GET, RequestMethod.POST,
                RequestMethod.DELETE,RequestMethod.OPTIONS,
                RequestMethod.PUT},
origins = "http://localhost:3000"
)
public class CategoryController {

    private final CategoryService categoryService;
    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @GetMapping
    public List<CategoryResponse> getCategoryByUserId(@RequestHeader String authorization) {

//        String token = authorization.substring(7);
        String userId = authService.validateUser(authorization);

        List<CategoryResponse> categories = categoryService.getCategoriesByUserId(UUID.fromString(userId));

        return categories;
    }

    @PostMapping
    public void saveCategories(@RequestBody MainCtgRequest mainCtgRequest, @RequestHeader("Authorization") String authorization) {

        String token = authorization.substring(7);
        String userId = authService.validateUser(token);
        System.out.println("User ID: " + userId);

        List<Long> categoryIds = mainCtgRequest.getCategoryIds();
        categoryService.saveCategories(UUID.fromString(userId), categoryIds);
    }

}
