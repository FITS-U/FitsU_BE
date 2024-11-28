package com.example.interestCategory.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryOfInterest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestCtgId;
    private Long categoryId;
    private UUID userId;
}
