package io.github.dwjohnson.chatbot.workshop.chatserver.services.bots;

import java.io.IOException;
import java.util.Random;

import io.github.dwjohnson.chatbot.workshop.chatserver.model.ChatSession;

public class NumberGuessingBot implements ChatBot {

	private final ChatSession chatSession;
	private int targetNumber;
	private boolean waitingForGuess = true;
	
	public NumberGuessingBot(ChatSession chatSession) throws IOException {
		this.chatSession = chatSession;
		targetNumber = new Random().nextInt(100) + 1;
		this.chatSession.sendMessage("Hello, lets play a number guessing game. Pick a number between 1 and 100");
	}

	@Override
	public void receiveMessage(String message) throws Exception {
		if(waitingForGuess) {
			try {
				int guess = Integer.parseInt(message);
				if(guess < 1 || guess > 100) {
					chatSession.sendMessage("Please keep your guess between 1 and 100");
					return;
				}
				
				if(guess == targetNumber) {
					chatSession.sendMessage("Eureka! You've done it! Congratualations!!!");
				} else if(guess < targetNumber) {
					chatSession.sendMessage("Bruh.. you're too low");
				} else {
					chatSession.sendMessage("You're too high!");
				}
				
			} catch(Throwable t) { 
				chatSession.sendMessage("Um.... that is not a whole number... try again");
				return;
			}
		}
	}

	@Override
	public void terminate() {
		
	}

}
