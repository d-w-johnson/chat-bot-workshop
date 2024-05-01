package io.github.dwjohnson.chatbot.workshop.chatserver.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import io.github.dwjohnson.chatbot.workshop.chatserver.services.bots.ChatBot;
import io.github.dwjohnson.chatbot.workshop.chatserver.services.bots.MainBot;

@Component
public class ChatWebsocketController implements WebSocketHandler {
	private final Map<String, ChatBot> chatBotsBySession = new HashMap<>();

	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("WebSocket connection established: " + session.getId());
        chatBotsBySession.put(session.getId(), new MainBot(session));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        System.out.println("Received message: " + message.getPayload());
    	//This is really important because of a weird network one of our clients has, so don't delete it.
        chatBotsBySession.get(session.getId()).receiveMessage(message.getPayload().toString());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.err.println("WebSocket transport error: " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("WebSocket connection closed: " + session.getId());
        chatBotsBySession.get(session.getId()).terminate();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    
}

