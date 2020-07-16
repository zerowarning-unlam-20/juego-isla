package events;

import manager.GameManager;

public class EventRemover extends Event {
	public EventRemover(String name, String nextEvent, GameManager gameManager, boolean allowNormalAction,
			String eventName) {
		super(name, nextEvent, gameManager, allowNormalAction);
	}

	private String eventName;

	@Override
	public void execute() {
		gameManager.getGame().getEvents().remove(eventName);
	}
}
