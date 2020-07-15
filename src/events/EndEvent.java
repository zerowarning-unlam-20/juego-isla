package events;

import manager.GameManager;
import tools.MessageType;

public class EndEvent extends Event {

	public EndEvent(String name, String nextEvent, GameManager gameManager, boolean allowNormalAction) {
		super(name, nextEvent, gameManager, allowNormalAction);
	}

	@Override
	public void execute() {
		gameManager.sendMessage(MessageType.STORY, "trigger", "-Game over-");
		gameManager.sendMessage(MessageType.STORY, "trigger", "¡Gracias por jugar!");
		gameManager.endGame();
	}
}
