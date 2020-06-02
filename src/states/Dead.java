package states;

import entities.Attack;
import entities.Entity;
import island.GameObject;
import items.Item;
import items.Location;
import items.Weapon;
import manager.GameManager;
import tools.MessageType;

public class Dead implements State {
	private Entity character;

	public Dead(Entity character) {
		this.character = character;
	}

	@Override
	public boolean open(GameObject item) {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean unlock(GameObject toUnlock) {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean look(GameObject object) {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean goTo(Location location) {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean grab(Item item) {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public State drink(Item item) {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return this;
	}

	@Override
	public boolean give(Item item, GameObject gameObject) {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean lookAround() {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean lookInventory() {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public boolean hit(Item item, GameObject gameObject) {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return false;
	}

	@Override
	public State heal(Double points) {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return this;
	}

	@Override
	public State recieveAttack(Attack attack) {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return this;
	}

	@Override
	public boolean attack(Weapon weapon, Entity objective) {
		GameManager.sendMessage(MessageType.EVENT, character, "Muert" + character.getTermination());
		return false;
	}

}
