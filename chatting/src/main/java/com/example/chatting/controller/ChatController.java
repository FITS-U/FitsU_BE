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
@RequestMapping("api/v1/chats")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/{userId}")
    public List<ChatResponse> getChatRoomList(@PathVariable UUID userId) {
        return chatService.getChatRoomList(userId);
    }

    @GetMapping("/{userId}/{chatRoomId}")
    public List<ChatResponse> getRoomById(@PathVariable UUID userId, @PathVariable Long chatRoomId) {
        return chatService.getRoomById(chatRoomId, userId);
    }

    @GetMapping("/count/{productId}")
    public int getCountRooms(@PathVariable Long productId) {
        return chatService.getCountRooms(productId);
    }

    @PostMapping("/")
    public ChatResponse addChatRoom(@RequestBody ChatRoom chatRoom) {
        return chatService.addChatRoom(chatRoom);
    }

    @DeleteMapping("/{chatRoomId}")
    public void deleteChatRoom(@PathVariable Long chatRoomId) {
        chatService.deleteChatRoom(chatRoomId);
    }
}
