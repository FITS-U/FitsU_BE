package com.example.chatting.service;

import com.example.chatting.domain.ChatMessage;
import com.example.chatting.request.ChatRoomRequest;
import com.example.chatting.response.ChatResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ChatService {
    List<ChatResponse> getChatRoomList(UUID userId);
    List<ChatResponse> getRoomById(Long chatRoomId, UUID userId);
    int getCountRooms(Long productId);
    ChatResponse addChatRoom(ChatRoomRequest request);
    void deleteChatRoom(Long chatRoomId);
    ChatMessage saveMessage(ChatMessage message);
}
