package states;

import entities.Attack;
import entities.Entity;
import island.GameObject;
import island.Location;
import items.Item;
import items.Weapon;
import tools.MessageType;

public class Dead implements State {
	private Entity character;

	public Dead(Entity character) {
		this.character = character;
	}

	@Override
	public boolean open(GameObject item) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean unlock(GameObject toUnlock) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean look(GameObject object) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean goTo(Location location) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean grab(Item item) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public State drink(Item item) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return this;
	}

	@Override
	public boolean give(Item item, GameObject gameObject) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean lookAround() {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean lookInventory() {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean hit(Item item, GameObject gameObject) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public State heal(Double points) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return this;
	}

	@Override
	public State recieveAttack(Attack attack) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return this;
	}

	@Override
	public boolean attack(Weapon weapon, Entity objective) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean talk(Entity other, String message) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean listen(Entity other, String message) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean use(Item item) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean read(Item item) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean create(Item item) {
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Caid" + character.getTermination());
		return false;
	}

}
