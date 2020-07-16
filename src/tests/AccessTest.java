package tests;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.Player;
import island.Area;
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
		Location s1 = new Location(Gender.M, "s1", "Inicio", true, true, new HashMap<String, Area>(),
				new HashMap<String, Access>());
		s1.addAccess(new Access(Gender.F, "puerta1", "Puerta azul", 0, false, true, null, "s2", null, DamageType.HACK));
		Location s2 = new Location(Gender.M, "s2", "Segundo lugar", true, true, new HashMap<String, Area>(),
				new HashMap<String, Access>());
		s2.addAccess(new Access(Gender.F, "puerta2", "Puerta azul", 0, false, true, null, "s1", null, DamageType.HACK));
		s2.addAccess(
				new Access(Gender.F, "puerta3", "Puerta azul", 0, true, false, null, "s3", "testKey", DamageType.HACK));

		Location s3 = new Location(Gender.M, "s3", "Tercer lugar", true, true, new HashMap<String, Area>(),
				new HashMap<String, Access>());

		HashMap<String, Location> locations = new HashMap<String, Location>();
		locations.put(s1.getName().toLowerCase(), s1);
		locations.put(s2.getName().toLowerCase(), s2);
		locations.put(s3.getName().toLowerCase(), s3);

		// Load empty NPC list
		HashMap<String, NPC> npcs = new HashMap<String, NPC>();

		// Load character
		character = new Player(gameManager, s1);

		// Load game into manager
		Game game = new Game(gameManager, character, locations, npcs, null);

		gameManager.setInternalGame(game);

		initialLocation = s1;
	}

	@Test
	public void accessTest1() {
		Assert.assertTrue(character.goTo("s2"));
	}

	@Test
	public void accessTest2() {
		Item key = new Key(Gender.F, "testKey", "Llave_test", 0);
		character.addItem(key);
		character.goTo("s2");
		character.unlock("s3", key.getName().toLowerCase());
		character.open("puerta azul");
		Assert.assertTrue(character.goTo("s3"));
	}

}
