package tests;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.Player;
import island.Location;
import items.Access;
import items.Item;
import items.types.Key;
import manager.Game;
import manager.GameManager;
import tools.DamageType;
import tools.Gender;

class AccessTest {

	GameManager gameManager;
	Player character;
	Location initialLocation;

	@BeforeEach
	void load() {
		gameManager = new GameManager(true);

		// Load locations
		Location s1 = new Location(Gender.M, "s1", "Inicio", true, new HashMap<String, Item>(),
				new HashMap<String, Access>());
		s1.addAccess(new Access(Gender.F, "Puerta", "Puerta azul", false, true, null, "s2", null, DamageType.HACK));
		Location s2 = new Location(Gender.M, "s2", "Segundo lugar", true, new HashMap<String, Item>(),
				new HashMap<String, Access>());
		s2.addAccess(new Access(Gender.F, "Puerta", "Puerta azul", false, true, null, "s1", null, DamageType.HACK));

		HashMap<String, Location> locations = new HashMap<String, Location>();
		locations.put(s1.getName().toLowerCase(), s1);
		locations.put(s2.getName().toLowerCase(), s2);

		// Load empty NPC list
		HashMap<String, NPC> npcs = new HashMap<String, NPC>();

		// Load character
		character = new Player(gameManager, s1);

		// Load game into manager
		Game game = new Game(gameManager, character, locations, npcs);

		gameManager.setInternalGame(game);

		initialLocation = s1;
	}

	@Test
	public void accessTest1() {
		Item key = new Key(Gender.F, "Llave", "Llave_test");
		character.addItem(key);
		Assert.assertTrue(character.goTo("s2"));
	}

}
