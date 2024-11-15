package com.example.chatting.service;

import com.example.chatting.domain.ChatMessage;
import com.example.chatting.domain.ChatRoom;
import com.example.chatting.repository.ChatMessageRepository;
import com.example.chatting.repository.ChatRepository;
import com.example.chatting.request.ChatRoomRequest;
import com.example.chatting.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public List<ChatResponse> getChatRoomList(UUID userId) {
        List<ChatRoom> all = chatRepository.findByUserId(userId);
        List<ChatResponse> list = all.stream().map(ChatResponse::from).toList();
        return list;
    }

    @Override
    public List<ChatResponse> getRoomById(Long chatRoomId, UUID userId) {
        List<ChatRoom> room = chatRepository.findByChatRoomIdAndUserId(chatRoomId, userId);
        List<ChatResponse> list = room.stream().map(ChatResponse::from).toList();
        return list;
    }

    @Override
    public int getCountRooms(Long productId) {
        return chatRepository.countByProductId(productId);
    }

    @Override
    public ChatResponse addChatRoom(ChatRoomRequest request) {
        ChatRoom chatRoom = request.toEntity();
        ChatRoom save = chatRepository.save(chatRoom);
        return ChatResponse.from(save);
    }

    @Override
    public void deleteChatRoom(Long chatRoomId) {
        chatRepository.deleteById(chatRoomId);
    }

    @Override
    public ChatMessage saveMessage(ChatMessage message) {
        return chatMessageRepository.save(message);
    }
}
