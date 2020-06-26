package events;

import items.Access;
import manager.GameManager;

public class AccessEvent extends Event {

	public AccessEvent(String name, String nextEvent, GameManager gameManager, String unlocks) {
		super(name, nextEvent, gameManager);
		this.unlocks = unlocks;
	}

	private String unlocks;

	@Override
	public void execute() {
		Access access = gameManager.getGame().getAccesses().get(unlocks);
		if (access != null) {
			access.unlock();
		}
		gameManager.getGame().pullTrigger(name);
	}

	@Override
	public void linkManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

}
