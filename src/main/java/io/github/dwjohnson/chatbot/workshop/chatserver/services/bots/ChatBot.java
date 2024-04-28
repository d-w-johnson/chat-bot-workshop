package io.github.dwjohnson.chatbot.workshop.chatserver.services.bots;

public interface ChatBot {
	void receiveMessage(String message) throws Exception;
	void terminate();
}
