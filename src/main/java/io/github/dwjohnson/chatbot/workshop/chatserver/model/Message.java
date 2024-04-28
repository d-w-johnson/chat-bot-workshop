package io.github.dwjohnson.chatbot.workshop.chatserver.model;

public class Message {
	private boolean sent;
	private String message;
	
	public Message(boolean sent, String message) {
		this.sent = sent;
		this.message = message;
	}

	public boolean isSent() {
		return sent;
	}
	
	public String getMessage() {
		return message;
	}
}
