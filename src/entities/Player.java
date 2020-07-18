package entities;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import island.Location;
import items.Inventory;
import manager.GameManager;
import tools.Gender;
import tools.Namber;
import tools.ui.Sound;

public class Player extends Entity {

	public Player(GameManager gameManager, Location location) {
		super(gameManager, Gender.M, Namber.S, "Test", "Test Character", location, new Inventory(),
				location.getName().toLowerCase());
		initialLocation = location.getName();
	}

	public Player(GameManager gameManager, Gender gender, Namber number, String name, String description,
			Inventory inventory, String initialLocation) {
		super(gameManager, gender, number, name, description, null, inventory, initialLocation);
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
		gameManager.getGame().pullTrigger("_dead", this);
	}

	@Override
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
