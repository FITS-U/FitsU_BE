package com.example.auth.admin.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Admin")
public class Admin {

    @Id
    private String adminId;

    @Column(nullable = false)
    private String adminName;

    @Column(nullable = false)
    private String password;
}
