package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.UserCharacter;
import island.Location;
import items.Item;
import items.ItemEffect;
import items.types.Consumable;
import items.types.Container;
import items.types.Source;
import manager.Game;
import manager.GameManager;
import tools.Gender;

class SingleContainerTest {
	private GameManager gameManager;
	private UserCharacter character;
	private Container bottle;
	private Source waterfall;

	@BeforeEach
	public void start() {
		gameManager = new GameManager(true);

		// Load locations
		Location s1 = new Location(1, Gender.M, "s1", "Inicio", true);
		ArrayList<Location> locations = new ArrayList<>();
		locations.add(s1);

		// Add waterfall to location
		Consumable water = new Consumable(4, Gender.F, "agua", "Agua de catarata", true, new ItemEffect(0D, null));
		waterfall = new Source(3, Gender.F, "Cataratas", "Cataratas con mucha agua", water);
		s1.addItem(waterfall);

		// Load character
		ArrayList<Item> inventory = new ArrayList<Item>();
		bottle = new Container(2, Gender.F, "Botella", "Botella de vidrio");
		inventory.add(bottle);
		character = new UserCharacter(gameManager, s1, inventory);

		// Load empty NPC list
		ArrayList<NPC> npcs = new ArrayList<>();

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
		Consumable water = new Consumable(4, Gender.F, "agua", "Agua de catarata", true, new ItemEffect(0D, null));
		bottle.setContent(water);
		Assert.assertEquals(bottle.getContent(), water);
	}

	@Test
	public void bottleTest3() { // Put water into a bottle
		Consumable water = new Consumable(4, Gender.F, "agua", "Agua de catarata", true, new ItemEffect(0D, null));
		character.grab(waterfall.getContent());
		Assert.assertEquals(((Container) character.getInventory().get(0)).getContent(), water);
	}
	/*
	 * @Test public void bottleTest4() { Location s1 = new Location(1, Gender.M,
	 * "s1", "Inicio", true); SingleContainer bottle = new SingleContainer(2,
	 * Gender.F, "Botella", "Botella de vidrio"); Consumable water = new
	 * Consumable(4, Gender.F, "agua", "Agua de catarata", true, new ItemEffect(0D,
	 * null)); }
	 */
}
