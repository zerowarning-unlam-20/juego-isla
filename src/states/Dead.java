package states;

import entities.Entity;
import island.GameObject;
import items.Item;
import items.Location;
import manager.GameManager;
import tools.ScreenOutput;

public class Dead implements State {
	private Entity character;

	public Dead(Entity character) {
		this.character = character;
	}

	@Override
	public void open(Item item) {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void unlock(Item toUnlock) {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void look(GameObject object) {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void goTo(Location location) {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void grab(Item item) {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public State drink(Item item) {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
		return this;
	}

	@Override
	public void give(Item item, GameObject gameObject) {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void lookAround() {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void lookInventory() {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public void hit(Item item, GameObject gameObject) {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
	}

	@Override
	public State heal(Double points) {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
		return this;
	}

	@Override
	public State recieveDamage(Double damage) {
		ScreenOutput.recieveMessage(character.getName(), "Muert" + character.getTermination());
		return this;
	}

}
