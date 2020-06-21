package tests;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.Player;
import island.Location;
import items.Item;
import items.ItemEffect;
import items.types.Consumable;
import items.types.Container;
import items.types.Source;
import manager.Game;
import manager.GameManager;
import tools.Gender;

class DispensersTest {
	private GameManager gameManager;
	private Player character;
	private Container bottle;
	private Source waterfall;

	@BeforeEach
	public void start() {
		gameManager = new GameManager(true);

		// Load locations
		Location s1 = new Location(Gender.M, "s1", "Inicio", true, new HashMap<>(), new HashMap<>());

		HashMap<String, Location> locations = new HashMap<>();
		locations.put(s1.getName().toLowerCase(), s1);

		// Add waterfall to location
		Consumable water = new Consumable(Gender.F, "agua", "Agua de catarata", true, new ItemEffect(0D, null));
		waterfall = new Source(Gender.F, "Cataratas", "Cataratas con mucha agua", water);
		s1.addItem(waterfall);

		// Load empty NPCs
		HashMap<String, NPC> npcs = new HashMap<>();

		// Load character
		HashMap<String, Item> inventory = new HashMap<>();
		bottle = new Container(Gender.F, "Botella", "Botella de vidrio");
		inventory.put(bottle.getName().toLowerCase(), bottle);
		character = new Player(gameManager, Gender.M, "TestName", "test", s1, inventory, "s1");

		// Load game
		Game game = new Game(gameManager, character, locations, npcs);
		gameManager.setInternalGame(game);
	}

	@Test
	public void bottleTest1() {
		Assert.assertEquals(null, bottle.getContent());
	}

	@Test
	public void bottleTest2() {
		Consumable water = new Consumable(Gender.F, "agua", "Agua de catarata", true, new ItemEffect(0D, null));
		bottle.setContent(water);
		Assert.assertEquals(bottle.getContent(), water);
	}

	@Test
	public void bottleTest3() { // Put water into a bottle
		Consumable water = new Consumable(Gender.F, "agua", "Agua de catarata", true, new ItemEffect(0D, null));
		character.grab(waterfall, water);
		Assert.assertEquals(((Container) character.getInventory().get("botella")).getContent(), water);
	}
}
