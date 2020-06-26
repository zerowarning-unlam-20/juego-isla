package events;

import manager.GameManager;

public abstract class Event {
	protected String name;
	protected String nextEvent;
	protected GameManager gameManager;

	public Event(String name, String nextEvent, GameManager gameManager) {
		this.name = name;
		this.nextEvent = nextEvent;
		this.gameManager = gameManager;
	}

	public abstract void execute();

	public void linkManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public String getName() {
		return name;
	}
}
