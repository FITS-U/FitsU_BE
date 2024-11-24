package com.example.interestCategory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class MainCtgRequest {
    private UUID userId;
    private List<Long> mainCtgIds;
}
