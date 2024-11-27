package com.example.interestCategory.controller;

import com.example.interestCategory.dto.CategoryResponse;
import com.example.interestCategory.dto.MainCtgRequest;
import com.example.interestCategory.global.CustomUserDetails;
import com.example.interestCategory.global.JwtUtils;
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
    private final JwtUtils jwtUtils;

    @GetMapping
    public List<CategoryResponse> getCategoryByUserId(@RequestHeader String authorization) throws AccessDeniedException {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);
        String currentUserId = jwtUtils.parseToken(token);

        List<CategoryResponse> categories = categoryService.getCategoriesByUserId(UUID.fromString(currentUserId));

        return categories;
    }

    @PostMapping
    public void saveCategories(@RequestBody MainCtgRequest mainCtgRequest, @RequestHeader String authorization) throws AccessDeniedException {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("유효한 인증 토큰이 필요합니다.");
        }

        String token = authorization.substring(7);


        String currentUserId = jwtUtils.parseToken(token);

        List<Long> categoryIds = mainCtgRequest.getCategoryIds();
        categoryService.saveCategories(UUID.fromString(currentUserId), categoryIds);
    }

}
