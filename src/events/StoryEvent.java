package events;

import manager.GameManager;
import tools.MessageType;

public class StoryEvent extends Event {
	private String message;

	public StoryEvent(String name, String nextEvent, GameManager gameManager, String message) {
		super(name, nextEvent, gameManager);
		this.message = message;
	}

	@Override
	public void execute() {
		gameManager.sendMessage(MessageType.STORY, "trigger", message);
		gameManager.getGame().pullTrigger(nextEvent);
	}

	@Override
	public void linkManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
}
