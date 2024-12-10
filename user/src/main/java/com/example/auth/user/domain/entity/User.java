package com.example.auth.user.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "User")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String RRNum;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String nickName;

}
