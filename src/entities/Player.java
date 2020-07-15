package entities;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import events.Event;
import island.Location;
import items.Inventory;
import manager.GameManager;
import states.State;
import tools.Gender;
import tools.ui.Sound;

public class Player extends Entity {

	public Player(GameManager gameManager, Location location) {
		super(gameManager, Gender.M, "Test", "Test Character", location, new Inventory(),
				location.getName().toLowerCase());
		initialLocation = location.getName();
	}

	public Player(GameManager gameManager, Gender gender, String name, String description, Inventory inventory,
			String initialLocation) {
		super(gameManager, gender, name, description, null, inventory, initialLocation);
	}

	public Player(GameManager gameManager, Gender gender, String name, String description, Location location,
			Inventory inventory, String initialLocation) {
		super(gameManager, gender, name, description, null, inventory, initialLocation);
	}

	public Player(Player character, String name, Gender gender) {
		super(character.gameManager, gender, name, character.description, null, character.inventory,
				character.initialLocation);
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void onDeath(Attack attack) {
		super.onDeath(attack);
		Sound.filePath = "sounds/dark.wav";
		try {
			gameManager.getSoundManager().stop();
			gameManager.getSoundManager().resetAudioStream();
			gameManager.getSoundManager().play();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			System.out.println("Error en sonido, ondeath: " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		gameManager.getGame().pullTrigger(this.getClass().getName() + "_" + "dead");
	}

	@Override
	public boolean grab(String name) {
		Event event = gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_grab_" + name);
		if (event == null || event.isNormalActionAllowed())
			return super.grab(name);
		return true;
	}

	@Override
	public boolean grab(String source, String name) {
		Event event = gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_grab_" + name);
		if (event == null || event.isNormalActionAllowed())
			return super.grab(source, name);
		return true;
	}

	@Override
	public boolean grab(String name, String source, String container) {
		Event event = gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_grab_" + name);
		if (event == null || event.isNormalActionAllowed())
			return super.grab(name, source, container);
		return true;
	}

	@Override
	public boolean unlock(String toUnlock, String keyName) {
		Event event = gameManager.getGame()
				.pullTrigger(this.getClass().getName().toLowerCase() + "_unlock_" + toUnlock);
		if (event == null || event.isNormalActionAllowed())
			return super.unlock(toUnlock, keyName);
		return true;
	}

	@Override
	public State drink(String name, String dispenserName) {
		Event event = gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_drink_" + name);
		if (event == null || event.isNormalActionAllowed())
			return super.drink(name, dispenserName);
		return state;
	}

	@Override
	public boolean look(String objectName) {
		Event event = gameManager.getGame()
				.pullTrigger(this.getClass().getName().toLowerCase() + "_look_" + objectName);
		if (event == null || event.isNormalActionAllowed())
			return super.look(objectName);
		return true;
	}

	@Override
	public boolean attack(String weapon, String target) {
		Event event = gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_attack_" + target);
		if (event == null || event.isNormalActionAllowed())
			return super.attack(weapon, target);
		return true;
	}

	@Override
	public boolean talk(String other, String message) {
		Event event = gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_talk_" + other);
		if (event == null || event.isNormalActionAllowed())
			return super.talk(other, message);
		return true;
	}

	@Override
	public boolean listen(String other, String message) {
		Event event = gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_listen_" + message);
		if (event == null || event.isNormalActionAllowed())
			return super.listen(other, message);
		return true;
	}

	@Override
	public boolean goTo(String name) {
		Event event = gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_goto_" + name);
		if (event == null || event.isNormalActionAllowed())
			return super.goTo(name);
		return true;
	}

	@Override
	public boolean use(String name) {
		Event event = gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_use_" + name);
		if (event == null || event.isNormalActionAllowed())
			return super.use(name);
		return true;
	}

	@Override
	public boolean inspect(String name) {
		Event event = gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_inspect_" + name);
		if (event == null || event.isNormalActionAllowed())
			return super.inspect(name);
		return true;
	}

	@Override
	public State eat(String itemName) {
		Event event = gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_eat_" + name);
		if (event == null || event.isNormalActionAllowed())
			return state.eat(itemName);
		return state;
	}
}
