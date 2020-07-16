package states;

import entities.Attack;
import entities.Entity;
import tools.MessageType;

public class Dead implements State {
	private Entity character;

	public Dead(Entity character) {
		this.character = character;
	}

	@Override
	public boolean open(String objectName) {

		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean unlock(String toUnlockName, String keyName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean look(String objectName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean goTo(String locationName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean grab(String itemName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean grab(String sourceName, String itemName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean grab(String itemName, String sourceName, String containerName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean lookAround() {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean lookInventory() {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean attack(String weaponName, String targetName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public State receiveAttack(Attack attack) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return this;
	}

	@Override
	public boolean talk(String otherName, String messageName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean listen(String otherName, String messageName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean use(String itemName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean read(String itemName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public boolean inspect(String itemName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public void lookState() {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
	}

	@Override
	public State drink(String name, String dispenserName) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return this;
	}

	@Override
	public boolean drop(String item) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return false;
	}

	@Override
	public State eat(String name) {
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Ya esta caid" + character.getTermination());
		return this;
	}

}
