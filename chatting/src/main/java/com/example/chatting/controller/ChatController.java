package com.example.chatting.controller;


import com.example.chatting.request.ChatRoomRequest;
import com.example.chatting.response.ChatResponse;
import com.example.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chats")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/")
    public List<ChatResponse> getChatRoomList(@AuthenticationPrincipal UserDetails userdetails) {
        UUID userId = UUID.fromString(userdetails.getUsername());
        return chatService.getChatRoomList(userId);
    }
    //@AuthenticationPrincipal UserDetails userdetails

    @GetMapping("/{chatRoomId}")
    public List<ChatResponse> getRoomById(@RequestHeader UUID userId, @PathVariable Long chatRoomId) {
        return chatService.getRoomById(chatRoomId, userId);
    }

    @GetMapping("/count/{productId}")
    public int getCountRooms(@PathVariable Long productId) {
        return chatService.getCountRooms(productId);
    }

    @PostMapping
    public ChatResponse addChatRoom(@RequestBody ChatRoomRequest request) {
        return chatService.addChatRoom(request);
    }

    @DeleteMapping("/{chatRoomId}")
    public void deleteChatRoom(@PathVariable Long chatRoomId) {
        chatService.deleteChatRoom(chatRoomId);
    }

}
