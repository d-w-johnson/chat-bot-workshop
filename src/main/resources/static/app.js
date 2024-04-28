let socket = null;

function connect() {
	const wsProtocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
    const wsHost = window.location.host;
    socket = new WebSocket(wsProtocol + '//' + wsHost + '/chat');
    
    socket.onopen = function(event) {
	    console.log("WebSocket connection established.");
	};
	
	socket.onmessage = function(event) {
	    console.log("Received message: " + event.data);
	    showMessage(event.data, 'Bot');
	};
	
	socket.onerror = function(event) {
	    console.error("WebSocket error: " + event);
	};
	
	socket.onclose = function(event) {
	    console.log("WebSocket connection closed.");
	};
}

function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value.trim();
    if (message !== '' && socket.readyState === WebSocket.OPEN) {
        socket.send(message);
        showMessage(message, 'Me');
        messageInput.value = '';
    } else {
        console.error("Message is empty or WebSocket connection is not open.");
    }
}

function handleKeyPress(event) {
    if (event.key === 'Enter') {
        sendMessage();
    }
}

function showMessage(message, sender) {
    const messagesDiv = document.getElementById('messages');

    const messageBubble = document.createElement('div');
    messageBubble.classList.add('bubble');
    messageBubble.classList.add(sender === 'Me' ? 'me' : 'bot')

    // Create label div for the sender
    const senderLabel = document.createElement('div');
    senderLabel.classList.add('sender-label');
    senderLabel.textContent = sender === 'Me' ? 'Me' : 'Fred Bot'; // Set sender label text
    messageBubble.appendChild(senderLabel);

    // Create div for the message content
    const messageContent = document.createElement('div');
    messageContent.classList.add('message-content');
    messageContent.textContent = message;
    messageBubble.appendChild(messageContent);

    // Insert the new message bubble at the beginning of the chat messages container
    if (messagesDiv.firstChild) {
        messagesDiv.insertBefore(messageBubble, messagesDiv.firstChild);
    } else {
        messagesDiv.appendChild(messageBubble);
    }
    
    // Scroll to the bottom of the chat messages container
    messagesDiv.scrollTop = messagesDiv.scrollHeight;
    window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });
}

document.addEventListener('DOMContentLoaded', function () {
    const messageInput = document.getElementById('messageInput');
    messageInput.addEventListener('keypress', handleKeyPress);
    
    connect();
});
