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
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void unlock(Item toUnlock) {
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void look(GameObject object) {
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void goTo(Location location) {
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void grab(Item item) {
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public State drink(Item item) {
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
		return this;
	}

	@Override
	public void give(Item item, GameObject gameObject) {
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void lookAround() {
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void lookInventory() {
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void hit(Item item, GameObject gameObject) {
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public State heal(Double points) {
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
		return this;
	}

	@Override
	public State recieveDamage(Double damage) {
		GameManager.getInstance().recieveMessage(character.getName(), "Muert" + character.getTermination());
		return this;
	}

}
