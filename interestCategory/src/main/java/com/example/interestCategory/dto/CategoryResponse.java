package com.example.interestCategory.dto;

import com.example.interestCategory.domain.CategoryOfInterest;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CategoryResponse(
        Long categoryId,
        UUID userId
) {
    public static CategoryResponse from (CategoryOfInterest categoryOfInterest) {
        return new CategoryResponse(
                categoryOfInterest.getCategoryId(),
                categoryOfInterest.getUserId()
        );
    }
}