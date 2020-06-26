package events;

import manager.GameManager;
import tools.MessageType;

public class EndEvent extends Event {

	public EndEvent(String name, String nextEvent, GameManager gameManager) {
		super(name, nextEvent, gameManager);
	}

	@Override
	public void execute() {
		gameManager.sendMessage(MessageType.STORY, "trigger", "-Game over-");
		gameManager.sendMessage(MessageType.STORY, "trigger", "¡Gracias por jugar!");
		gameManager.endGame();
	}

	@Override
	public void linkManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
}
