package com.example.chatting.controller;

import com.example.chatting.domain.ChatRoom;
import com.example.chatting.response.ChatResponse;
import com.example.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chats/{userId}")
    public List<ChatResponse> getChatRoomList(UUID userId) {
        return chatService.getChatRoomList(userId);
    }

    @GetMapping("/chats/{userId}/{chatRoomId}")
    public List<ChatResponse> getRoomById(@PathVariable UUID userId, @PathVariable Long chatRoomId) {
        return chatService.getRoomById(chatRoomId, userId);
    }

    @GetMapping("/chats/{productId)")
    public int getCountRooms(@PathVariable Long productId) {
        return chatService.getCountRooms(productId);
    }

    @PostMapping("/chats/{productId}")
    public ChatResponse addChatRoom(@PathVariable ChatRoom chatRoom, @PathVariable Long productId) {
        return chatService.addChatRoom(chatRoom, productId);
    }

    @DeleteMapping("/chats/{userId}/{chatRoomId}")
    public void deleteChatRoom(@PathVariable Long chatRoomId) {
        chatService.deleteChatRoom(chatRoomId);
    }
}
