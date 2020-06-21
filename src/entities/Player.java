package entities;

import java.util.HashMap;

import island.Location;
import items.Item;
import manager.GameManager;
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
}
