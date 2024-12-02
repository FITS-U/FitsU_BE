package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {
    private List<CategoryResponse> categories;
    private List<LogResponse> logs;
}
