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
	public void open(Item item) {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
	}

	@Override
	public void unlock(Item toUnlock) {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
	}

	@Override
	public void look(GameObject object) {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
	}

	@Override
	public void goTo(Location location) {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
	}

	@Override
	public void grab(Item item) {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
	}

	@Override
	public State drink(Item item) {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
		return this;
	}

	@Override
	public void give(Item item, GameObject gameObject) {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
	}

	@Override
	public void lookAround() {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
	}

	@Override
	public void lookInventory() {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
	}

	@Override
	public void hit(Item item, GameObject gameObject) {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
	}

	@Override
	public State heal(Double points) {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
		return this;
	}

	@Override
	public State recieveDamage(Double damage) {
		GameManager.recieveMessage(character, "Muert" + character.getTermination());
		return this;
	}

}
