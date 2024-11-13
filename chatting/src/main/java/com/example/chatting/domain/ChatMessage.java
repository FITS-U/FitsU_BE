package com.example.chatting.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private String content;
    private String messageType;
    private String imageUrl;
    private Boolean read;
    @CreatedDate @Builder.Default
    private LocalDateTime createdAt=LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "CHAT_ROOM_ID")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "SENDER_ID")
    private User user;
}
