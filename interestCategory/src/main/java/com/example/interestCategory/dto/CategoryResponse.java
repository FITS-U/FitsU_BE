package com.example.interestCategory.dto;

import com.example.interestCategory.domain.CategoryOfInterest;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CategoryResponse(
        Long categoryId
) {
    public static CategoryResponse from (CategoryOfInterest categoryOfInterest) {
        return new CategoryResponse(
                categoryOfInterest.getCategoryId()
        );
    }
}
