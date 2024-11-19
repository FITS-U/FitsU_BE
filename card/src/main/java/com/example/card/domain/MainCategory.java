package com.example.card.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MAIN_CATEGORY")
public class MainCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mainCtgId;
    private String mainCtgName;
}
