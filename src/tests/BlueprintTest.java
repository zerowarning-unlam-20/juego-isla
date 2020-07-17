package tests;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.Player;
import island.Location;
import items.Inventory;
import items.types.Blueprint;
import items.types.Resource;
import items.types.Weapon;
import manager.Game;
import manager.GameManager;
import tools.DamageType;
import tools.Gender;
import tools.Namber;
import tools.ResourceType;

class BlueprintTest {
	private GameManager gameManager;
	private Player player;
	private Weapon crossbow;

	@BeforeEach
	public void load() {
		gameManager = new GameManager(true);

		// Load environment
		Location s1 = new Location(Gender.M, Namber.S, "s1", "Inicio", true, true, new HashMap<>(), new HashMap<>());
		HashMap<String, Location> locations = new HashMap<>();
		locations.put(s1.getName().toLowerCase(), s1);

		// NPC empty list (constructor purposes)
		HashMap<String, NPC> npcs = new HashMap<>();

		// Load character & inventory
		Resource wood1 = new Resource(Gender.F, Namber.S, "Madera de palmera", "Madera_test_1", 0, ResourceType.WOOD,
				2);
		Resource stone = new Resource(Gender.M, Namber.S, "Piedra", "Piedra_test", 0, ResourceType.STONE, 1);
		Inventory inventory = new Inventory();
		inventory.addItem(wood1);
		inventory.addItem(stone);
		player = new Player(gameManager, Gender.M, Namber.S, "Test", "Test_desc", inventory, "s1");
		// crossbow & blueprint
		HashMap<ResourceType, Integer> requirements = new HashMap<>();
		requirements.put(wood1.getResourceType(), 2);
		requirements.put(stone.getResourceType(), 1);
		crossbow = new Weapon(Gender.F, Namber.S, "Ballesta", "ballesta_desc", 0, DamageType.SHOT, 100d);
		Blueprint crossBlueprint = new Blueprint(Gender.M, Namber.S, "Plano de ballesta", "Plano_desc", 0, requirements,
				crossbow);

		// Add crossbow to character
		player.addItem(crossBlueprint);

		// Load game
		Game game = new Game(gameManager, player, locations, npcs, null);
		gameManager.setInternalGame(game);
	}

	@Test
	public void blueprintTest1() {
		player.use("plano de ballesta");
		Assert.assertEquals(crossbow, player.getInventory().getItem("ballesta"));
	}

	@Test
	public void blueprintTest2() {
		player.removeItem(player.getInventory().getItem("madera de palmera"));
		Assert.assertFalse(player.use("plano de ballesta"));
		Assert.assertNotEquals(crossbow, player.getInventory().getItem("ballesta"));
	}

}
