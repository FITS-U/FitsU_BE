package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AdDataResponse {
    private List<CategoryResponse> categories;
    private List<LogResponse> logs;
}
