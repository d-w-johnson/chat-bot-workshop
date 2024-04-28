package io.github.dwjohnson.chatbot.workshop.chatserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import io.github.dwjohnson.chatbot.workshop.chatserver.controllers.ChatWebsocketController;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {
	
	private final ChatWebsocketController websocketController;
	
    public WebsocketConfig(ChatWebsocketController websocketController) {
		this.websocketController = websocketController;
	}

	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(websocketController, "/chat")
        	.setAllowedOriginPatterns("*");
    }
}

