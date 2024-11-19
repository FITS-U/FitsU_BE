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
@Table(name = "SUB_CATEGORY")
public class SubCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCtgId;
    private String subCtgName;

    @ManyToOne
    @JoinColumn(name = "MAIN_CTG_ID")
    private MainCategory mainCategory;

}
