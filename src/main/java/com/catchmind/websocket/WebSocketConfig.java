package com.catchmind.websocket;

import com.catchmind.chat.ChatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Profile("!stomp")
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /*@Autowired
    LobbySocketHandler lobbySocketHandler;*/

    @Autowired
    private ChatHandler chatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "/wa/chat").setAllowedOrigins("*").withSockJS();

    }

}
