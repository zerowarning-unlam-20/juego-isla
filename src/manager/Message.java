package manager;

import tools.MessageType;

public class Message {
	private String content;
	private MessageType messageType;

	public Message(String content, MessageType messageType) {
		this.content = content;
		this.messageType = messageType;
	}

	public String getContent() {
		return content;
	}

	public MessageType getMessageType() {
		return messageType;
	}
}
