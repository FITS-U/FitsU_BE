package com.example.chatting.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub"); // 메시지 수신 요청 엔드포인트
        registry.setApplicationDestinationPrefixes("/pub"); // 메시지 송신하는 엔드포인트
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // stomp 접속 주소 url = ws://localhost:8080/ws
        registry.addEndpoint("/ws") // 클라이언트가 연결할 엔드포인트
                .setAllowedOrigins("*")
                .withSockJS(); // webSocket이 지원되지 않는 브라우저 대비
    }
}

