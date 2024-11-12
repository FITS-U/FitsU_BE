package com.example.chatting.response;

import com.example.chatting.domain.ChatRoom;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChatResponse(Long chatRoomId, Long productId, LocalDateTime createdAt, UUID userId) {

    public static ChatResponse from(ChatRoom chatRoom) {
        return new ChatResponse(chatRoom.getChatRoomId(), chatRoom.getProductId(), chatRoom.getCreatedAt(), chatRoom.getUserId());
    }
}
