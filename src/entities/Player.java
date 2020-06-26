package entities;

import java.util.HashMap;

import island.Location;
import items.Item;
import manager.GameManager;
import states.State;
import tools.Gender;

public class Player extends Entity {

	public Player(GameManager gameManager, Location location) {
		super(gameManager, Gender.M, "Test", "Test Character", location, new HashMap<>(),
				location.getName().toLowerCase());
		initialLocation = location.getName();
	}

	public Player(GameManager gameManager, Gender gender, String name, String description,
			HashMap<String, Item> inventory, String initialLocation) {
		super(gameManager, gender, name, description, null, inventory, initialLocation);
	}

	public Player(GameManager gameManager, Gender gender, String name, String description, Location location,
			HashMap<String, Item> inventory, String initialLocation) {
		super(gameManager, gender, name, description, null, inventory, initialLocation);
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void onDeath(Attack attack) {
		super.onDeath(attack);
		gameManager.getGame().pullTrigger(this.getClass().getName() + "_" + "dead");
	}

	@Override
	public boolean grab(String name) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_grab_" + name);
		return super.grab(name);
	}

	@Override
	public boolean grab(String source, String name) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_grab_" + name);
		return super.grab(source, name);
	}

	@Override
	public boolean grab(String name, String source, String container) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_grab_" + name);
		return super.grab(name, source, container);
	}

	@Override
	public boolean unlock(String toUnlock, String keyName) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_unlock_" + toUnlock);
		return super.unlock(toUnlock, keyName);
	}

	@Override
	public State drink(String name, String dispenserName) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_drink_" + name);
		return super.drink(name, dispenserName);
	}

	@Override
	public boolean look(String objectName) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_look_" + objectName);
		return super.look(objectName);
	}

	@Override
	public boolean attack(String weapon, String target) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_attack_" + target);
		return super.attack(weapon, target);
	}

	@Override
	public boolean talk(String other, String message) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_talk_" + other);
		return super.talk(other, message);
	}

	@Override
	public boolean listen(String other, String message) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_listen_" + message);
		return super.listen(other, message);
	}

	@Override
	public boolean goTo(String name) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_goto_" + name);
		return super.goTo(name);
	}

	@Override
	public boolean use(String name) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_use_" + name);

		return super.use(name);
	}

	@Override
	public boolean inspect(String name) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_inspect_" + name);

		return super.inspect(name);
	}
}
