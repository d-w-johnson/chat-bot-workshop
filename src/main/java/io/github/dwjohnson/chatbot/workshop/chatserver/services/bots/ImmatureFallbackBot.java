package io.github.dwjohnson.chatbot.workshop.chatserver.services.bots;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.github.dwjohnson.chatbot.workshop.chatserver.model.ChatSession;
import io.github.dwjohnson.chatbot.workshop.chatserver.model.Message;

public class ImmatureFallbackBot implements ChatBot {
	private final ChatSession chatSession;
	private final static List<String> possibleResponses = Arrays.asList(
			"I know you are, but what am I?",
			"I'm good, how are you?",
			"I'm not programmed to answer that question...",
			"I don't know who I am or what I'm doing"
			);

	public ImmatureFallbackBot(ChatSession chatSession) {
		this.chatSession = chatSession;
	}

	@Override
	public void receiveMessage(String message) throws IOException {
		int count = 0;
		for(Message m : chatSession.getMessages()) {
			if(!m.isSent() && message.equalsIgnoreCase(m.getMessage())) {
				count++;
			}
		}
		if(count > 1) {
			chatSession.sendMessage("You've told me that, like... " + count + " times bruh");
			return;
		}
		
		Random random = new Random();
		int randomNumber = random.nextInt(4);
		chatSession.sendMessage(possibleResponses.get(randomNumber));
		return;
		
//		chatSession.sendMessage("Look at me, I'm " + chatSession.getUserName() + ": \"" + message + "\" BWAHAHAHAHAHAHAHA!!!!!!");
	}

	@Override
	public void terminate() {

	}

}
