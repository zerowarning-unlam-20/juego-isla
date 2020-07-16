package tests;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.Player;
import island.Location;
import items.Access;
import items.types.Text;
import manager.Game;
import manager.GameManager;
import tools.DamageType;
import tools.Gender;

class BookTest {
	Player character;
	GameManager gameManager;

	@BeforeEach
	public void start() {
		gameManager = new GameManager(true);

		// Load environment
		Location cave = new Location(Gender.M, "cueva", "Inicio", true, true, new HashMap<>(), new HashMap<>());
		Location outside = new Location(Gender.M, "afuera", "afuera", true, true, new HashMap<>(), new HashMap<>());
		HashMap<String, Location> locations = new HashMap<>();
		locations.put(cave.getName().toLowerCase(), cave);
		locations.put(outside.getName().toLowerCase(), outside);

		// NPC empty list (constructor purposes)
		HashMap<String, NPC> npcs = new HashMap<>();

		// Load accesses
		Access exit = new Access(Gender.F, "salida", "salida de cueva", 0, false, true, null, "afuera", null,
				DamageType.HACK);
		cave.addAccess(exit);

		// Load character
		character = new Player(gameManager, cave);

		// Load game
		Game game = new Game(gameManager, character, locations, npcs, null);
		gameManager.setInternalGame(game);
	}

	@Test
	public void bookTest1() {
		Text note = new Text(Gender.F, "Hoja", "Hoja del viajero", "Se necesitan troncos y "
				+ "lianas para poder armar la canoa. " + "Tambien llevar provisiones (IMPORTANTE COMIDA) y un remo", 0);
		character.addItem(note);
		Assert.assertFalse(character.look("hoja"));
	}

	@Test
	public void bookTest2() {
		Text note = new Text(Gender.F, "Hoja", "Hoja del viajero", "Se necesitan troncos y "
				+ "lianas para poder armar la canoa. " + "Tambien llevar provisiones (IMPORTANTE COMIDA) y un remo", 0);
		character.addItem(note);
		character.goTo("afuera");
		Assert.assertTrue(character.read("hoja"));
	}

}
