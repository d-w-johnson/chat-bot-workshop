package io.github.dwjohnson.chatbot.workshop.chatserver.services.bots;

import java.io.IOException;

import io.github.dwjohnson.chatbot.workshop.chatserver.model.ChatSession;

public class ImmatureFallbackBot implements ChatBot {
	private final ChatSession chatSession;

	public ImmatureFallbackBot(ChatSession chatSession) {
		this.chatSession = chatSession;
	}

	@Override
	public void receiveMessage(String message) throws IOException {
		//TODO: send "You already said that..." if the message is in the chat history more than once
		
		//TODO: Add different messages that can be sent (example commit change)
		
		chatSession.sendMessage("Look at me, I'm " + chatSession.getUserName() + ": \"" + message + "\" BWAHAHAHAHAHAHAHA!!!!!!");
	}

	@Override
	public void terminate() {

	}

}
