package events;

import manager.GameManager;

public abstract class Event {
	protected String name;
	protected String nextEvent;
	protected GameManager gameManager;
	protected boolean allowNormalAction;

	public Event(String name, String nextEvent, GameManager gameManager, boolean allowNormalAction) {
		this.name = name;
		this.nextEvent = nextEvent;
		this.gameManager = gameManager;
		this.allowNormalAction = allowNormalAction;
	}

	public abstract void execute();

	public void linkManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public String getName() {
		return name;
	}

	public boolean isNormalActionAllowed() {
		return allowNormalAction;
	}
}
