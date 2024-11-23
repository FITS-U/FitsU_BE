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
    private UUID userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String RRNum;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String nickName;

    public static User createUserWithPhoneNum(String phoneNum) {
        User user = new User();
        user.phoneNum = phoneNum;
        return user;
    }

    public User withPhoneNumber(String phoneNum){
        this.phoneNum = phoneNum;
        return this;
    }
}
