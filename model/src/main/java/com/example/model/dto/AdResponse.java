package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdResponse {
    private Long card_id;
    private String card_name;
    private String adCopy1;
    private String adCopy2;
    private String image_url;
}
