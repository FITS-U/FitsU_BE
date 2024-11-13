package com.example.chatting.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;
    private Long productId;
    private UUID userId;
    @CreatedDate
    @Builder.Default
    private LocalDateTime createdAt=LocalDateTime.now();

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatParticipant> participants = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User user;

}
