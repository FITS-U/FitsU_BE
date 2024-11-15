package com.example.chatting.controller;

import com.example.chatting.domain.ChatMessage;
import com.example.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/message")
    public void sendChatMessage(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        ChatMessage saveMessage = chatService.saveMessage(message);

        messagingTemplate.convertAndSendToUser(saveMessage.getUserId().toString(), "/sub/messages", saveMessage);
    }
}
