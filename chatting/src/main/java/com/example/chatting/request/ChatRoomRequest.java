package com.example.chatting.request;

import com.example.chatting.domain.ChatRoom;

import java.util.UUID;


public record ChatRoomRequest(
        Long productId,
        UUID userId
){
    public ChatRoom toEntity() {
        return ChatRoom.builder()
                .productId(productId)
                .userId(userId)
                .build();
    }
}
