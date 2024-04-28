package io.github.dwjohnson.chatbot.workshop.chatserver.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class ChatSession {
	private final WebSocketSession websocketSession;
	private String userName;
	private final List<Message> messages;
	
	public ChatSession(WebSocketSession websocketSession) {
		this.websocketSession = websocketSession;
		this.messages = new LinkedList<>();
	}
	
	public WebSocketSession getWebsocketSession() {
		return websocketSession;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void addReceivedMessage(String message) {
		messages.add(new Message(false, message));
	}
	
	public void sendMessage(String message) throws IOException {
		messages.add(new Message(true, message));
		websocketSession.sendMessage(new TextMessage(message));
	}
}
