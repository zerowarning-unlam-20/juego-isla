package tests;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.Player;
import island.Location;
import items.Access;
import manager.Game;
import manager.GameManager;
import tools.DamageType;
import tools.Gender;

class LocationTest {
	GameManager gameManager;
	Player character;
	Location initialLocation;

	@BeforeEach
	void load() {
		gameManager = new GameManager(true);

		// Load locations
		Location s1 = new Location(Gender.M, "s1", "Inicio", true, new HashMap<>(), new HashMap<>());
		Location s2 = new Location(Gender.M, "s2", "Inicio", true, new HashMap<>(), new HashMap<>());
		Location s3 = new Location(Gender.M, "s3", "Inicio", true, new HashMap<>(), new HashMap<>());
		Location s4 = new Location(Gender.M, "s4", "Inicio", true, new HashMap<>(), new HashMap<>());
		Location s5 = new Location(Gender.M, "s5", "Inicio", true, new HashMap<>(), new HashMap<>());
		Location s6 = new Location(Gender.M, "s6", "Inicio", true, new HashMap<>(), new HashMap<>());

		// Load accesses
		s1.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s2", null, DamageType.BLUNT));

		s2.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s1", null, DamageType.BLUNT));
		s2.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s3", null, DamageType.BLUNT));
		s2.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s5", null, DamageType.BLUNT));
		s2.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s6", null, DamageType.BLUNT));

		s3.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s2", null, DamageType.BLUNT));
		s3.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s4", null, DamageType.BLUNT));

		s4.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s3", null, DamageType.BLUNT));
		s4.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s5", null, DamageType.BLUNT));

		s5.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s4", null, DamageType.BLUNT));
		s5.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s2", null, DamageType.BLUNT));

		s6.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s2", null, DamageType.BLUNT));

		HashMap<String, Location> locations = new HashMap<>();
		locations.put(s1.getName().toLowerCase(), s1);
		locations.put(s2.getName().toLowerCase(), s2);
		locations.put(s3.getName().toLowerCase(), s3);
		locations.put(s4.getName().toLowerCase(), s4);
		locations.put(s5.getName().toLowerCase(), s5);
		locations.put(s6.getName().toLowerCase(), s6);

		// Load character & empty NPC list
		character = new Player(gameManager, s1);

		// NPC empty list (constructor purposes)
		HashMap<String, NPC> npcs = new HashMap<>();

		// Load game
		Game game = new Game(gameManager, character, locations, npcs, null);
		gameManager.setInternalGame(game);

		initialLocation = s1;
	}

	@Test
	void moveTest1() {
		character.goTo("s2");
		Assert.assertEquals("s2", character.getLocation().getName());
	}

	@Test
	void moveTest2() {
		Assert.assertFalse(character.goTo("s6"));
	}

	@Test
	void moveTest3() {
		character.goTo("s2");
		character.goTo("s5");
		Assert.assertTrue(character.goTo("s4"));
		Assert.assertEquals("s4", character.getLocation().getName());
	}

	@Test
	void moveTest4() {
		character.goTo("s2");
		character.goTo("s5");
		character.goTo("s4");
		Assert.assertFalse(character.goTo("s2"));
		Assert.assertFalse(character.goTo("s1"));
	}

	@Test
	void moveTest5() {
		Assert.assertFalse(character.goTo("s16"));
	}

}
