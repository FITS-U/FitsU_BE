package com.example.chatting.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatParticipant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;

    @ManyToOne
    @JoinColumn(name = "CHAT_ROOM_ID")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
