package entities;

import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import island.Location;
import items.Item;
import manager.GameManager;
import states.State;
import tools.Gender;
import tools.ui.Sound;

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
		if (gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_grab_" + name))
			return true;
		return super.grab(name);
	}

	@Override
	public boolean grab(String source, String name) {
		if (gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_grab_" + name))
			return true;
		return super.grab(source, name);
	}

	@Override
	public boolean grab(String name, String source, String container) {
		if (gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_grab_" + name))
			return true;
		return super.grab(name, source, container);
	}

	@Override
	public boolean unlock(String toUnlock, String keyName) {
		if (gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_unlock_" + toUnlock))
			return true;
		return super.unlock(toUnlock, keyName);
	}

	@Override
	public State drink(String name, String dispenserName) {
		gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_drink_" + name);

		return super.drink(name, dispenserName);
	}

	@Override
	public boolean look(String objectName) {
		if (gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_look_" + objectName))
			return true;
		return super.look(objectName);
	}

	@Override
	public boolean attack(String weapon, String target) {
		if (gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_attack_" + target))
			return true;
		return super.attack(weapon, target);
	}

	@Override
	public boolean talk(String other, String message) {
		if (gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_talk_" + other))
			return true;
		return super.talk(other, message);
	}

	@Override
	public boolean listen(String other, String message) {
		if (gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_listen_" + message))
			return true;
		return super.listen(other, message);
	}

	@Override
	public boolean goTo(String name) {
		if (gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_goto_" + name))
			return true;
		return super.goTo(name);
	}

	@Override
	public boolean use(String name) {
		if (gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_use_" + name))
			return true;
		return super.use(name);
	}

	@Override
	public boolean inspect(String name) {
		if (gameManager.getGame().pullTrigger(this.getClass().getName().toLowerCase() + "_inspect_" + name))
			return true;
		return super.inspect(name);
	}
}
