package com.example.chatting.repository;

import com.example.chatting.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
    int countByProductId(Long productId);
    List<ChatRoom> findByChatRoomIdAndUserId(Long roomId, UUID userId);
}
