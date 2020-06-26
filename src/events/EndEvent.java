package events;

import manager.GameManager;
import tools.MessageType;

public class EndEvent extends Event {

	public EndEvent(String name, String nextEvent, GameManager gameManager) {
		super(name, nextEvent, gameManager);
	}

	@Override
	public void execute() {
		gameManager.sendMessage(MessageType.STORY, "trigger", "Fin del juego");
		gameManager.getGame().pullTrigger(nextEvent);
	}

	@Override
	public void linkManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
}
