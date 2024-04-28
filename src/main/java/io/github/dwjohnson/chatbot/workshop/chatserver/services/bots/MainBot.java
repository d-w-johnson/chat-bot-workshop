package io.github.dwjohnson.chatbot.workshop.chatserver.services.bots;

import java.io.IOException;

import org.springframework.web.socket.WebSocketSession;

import io.github.dwjohnson.chatbot.workshop.chatserver.model.ChatSession;

public class MainBot implements ChatBot {
	private final ChatSession chatSession;
	private ChatBot activeBot;
	private ChatBot fallbackBot;
	
	public MainBot(WebSocketSession websocketSession) throws IOException {
		chatSession = new ChatSession(websocketSession);
		chatSession.sendMessage("Hi, I'm Fred Bot! What is your name?");
		fallbackBot = new ImmatureFallbackBot(chatSession);
	}

	@Override
	public void receiveMessage(String message) throws Exception {
		chatSession.addReceivedMessage(message);
		
		Thread.sleep(1000); //Sleep for 1 second so that the response isn't so fast
		
		if(chatSession.getUserName() == null) {
			chatSession.setUserName(message);
			chatSession.sendMessage("Hi " + message + "! How can I help you?");
			return;
		}
		
		if(activeBot != null) {
			activeBot.receiveMessage(message);
			return;
		}
		
		//TODO: Add help menu and stuff that can cause different types of active bots to be set
		
		if(fallbackBot != null) {
			fallbackBot.receiveMessage(message);
			return;
		}
		
		chatSession.sendMessage("I don't know what happened, but I'm at a loss for words...");
	}

	@Override
	public void terminate() {
		if(activeBot != null) {
			activeBot.terminate();
		}
		if(fallbackBot != null) {
			fallbackBot.terminate();
		}
	}
}
