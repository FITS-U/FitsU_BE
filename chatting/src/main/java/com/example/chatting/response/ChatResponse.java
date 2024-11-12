package com.example.chatting.response;

import com.example.chatting.domain.ChatRoom;

import java.time.LocalDateTime;

public record ChatResponse(Long chatRoomId, Long productId, LocalDateTime createdAt) {

    public static ChatResponse from(ChatRoom chatRoom) {
        return new ChatResponse(chatRoom.getChatRoomId(), chatRoom.getProductId(), chatRoom.getCreatedAt());
    }
}
