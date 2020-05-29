package states;

import entities.Entity;
import island.GameObject;
import items.Item;
import items.Location;
import manager.GameManager;

public class Dead implements State {
	private Entity character;

	public Dead(Entity character) {
		this.character = character;
	}

	@Override
	public boolean open(Item item) {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean unlock(Item toUnlock) {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean look(GameObject object) {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean goTo(Location location) {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean grab(Item item) {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public State drink(Item item) {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return this;
	}

	@Override
	public boolean give(Item item, GameObject gameObject) {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean lookAround() {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean lookInventory() {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean hit(Item item, GameObject gameObject) {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public State heal(Double points) {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return this;
	}

	@Override
	public State recieveDamage(Double damage) {
		GameManager.sendMessage(character, "Muert" + character.getTermination());
		return this;
	}

}
