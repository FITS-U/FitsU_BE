package com.example.chatting.service;

import com.example.chatting.domain.ChatRoom;
import com.example.chatting.repository.ChatRepository;
import com.example.chatting.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor

public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public List<ChatResponse> getChatRoomList(UUID userId) {
        List<ChatRoom> all = chatRepository.findAll();
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
    public ChatResponse addChatRoom(ChatRoom chatRoom, Long productId) {
        ChatRoom save = chatRepository.save(chatRoom);
        return ChatResponse.from(save);
    }

    @Override
    public void deleteChatRoom(Long chatRoomId) {
        chatRepository.deleteById(chatRoomId);
    }
}
