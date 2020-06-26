package tests;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.Player;
import island.Area;
import island.Location;
import items.Effect;
import items.Item;
import items.types.Bottle;
import items.types.Liquid;
import items.types.Source;
import manager.Game;
import manager.GameManager;
import tools.Gender;

class DispensersTest {
	private GameManager gameManager;
	private Player character;
	private Bottle bottle;
	private Source waterfall;

	@BeforeEach
	public void start() {
		gameManager = new GameManager(true);
		Area itemArea = new Area(Gender.M, "itemarea", "ItemArea", new HashMap<>());
		HashMap<String, Area> areas = new HashMap<>();
		areas.put(itemArea.getName(), itemArea);
		// Load locations
		Location s1 = new Location(Gender.M, "s1", "Inicio", true, areas, new HashMap<>());
		HashMap<String, Location> locations = new HashMap<>();
		locations.put(s1.getName().toLowerCase(), s1);

		// Add waterfall to location
		Liquid water = new Liquid(Gender.F, "agua", "Agua de catarata", new Effect(0D, null));
		waterfall = new Source(Gender.F, "catarata", "Cataratas con mucha agua", water);
		s1.getArea("itemarea").addItem(waterfall);

		// Load empty NPCs
		HashMap<String, NPC> npcs = new HashMap<>();

		// Load character
		HashMap<String, Item> inventory = new HashMap<>();
		bottle = new Bottle(Gender.F, "Botella", "Botella de vidrio");
		inventory.put(bottle.getName().toLowerCase(), bottle);
		character = new Player(gameManager, Gender.M, "TestName", "test", s1, inventory, "s1");

		// Load game
		Game game = new Game(gameManager, character, locations, npcs, null);
		gameManager.setInternalGame(game);
	}

	@Test
	public void bottleTest1() {
		bottle.empty();
		Assert.assertEquals(null, bottle.getContent());
	}

	@Test
	public void bottleTest2() {
		Liquid water = new Liquid(Gender.F, "agua", "Agua de catarata", new Effect(0D, null));
		bottle.setContent(water);
		Assert.assertEquals(bottle.getContent(), water);
	}

	@Test
	public void bottleTest3() { // Put water into a bottle
		Liquid water = new Liquid(Gender.F, "agua", "Agua de catarata", new Effect(0D, null));
		character.grab("agua", "catarata", "botella");
		Assert.assertEquals(water, ((Bottle) character.getInventory().get("botella")).getContent());
	}
}