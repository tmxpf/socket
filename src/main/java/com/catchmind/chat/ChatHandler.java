package com.catchmind.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Profile("!stomp")
@Component
public class ChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatRoomRepository repository;

    @Autowired
    public ChatHandler(ObjectMapper objectMapper, ChatRoomRepository chatRoomRepository) {
        this.objectMapper = objectMapper;
        this.repository = chatRoomRepository;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{

        String payload = message.getPayload();
        log.info("payload: {}", payload);

        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom chatRoom = repository.getChatRoom(chatMessage.getChatRoomId());
        chatRoom.handleMessage(session, chatMessage, objectMapper);
    }

}
