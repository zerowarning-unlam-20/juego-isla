package tests;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.Player;
import island.Location;
import items.Item;
import items.types.Blueprint;
import items.types.Resource;
import items.types.Weapon;
import manager.Game;
import manager.GameManager;
import tools.DamageType;
import tools.Gender;
import tools.ResourceType;

class BlueprintTest {
	private GameManager gameManager;
	private Player player;
	private Weapon crossbow;

	@BeforeEach
	public void load() {
		gameManager = new GameManager(true);

		// Load environment
		Location s1 = new Location(Gender.M, "s1", "Inicio", true, new HashMap<>(), new HashMap<>());
		HashMap<String, Location> locations = new HashMap<>();
		locations.put(s1.getName().toLowerCase(), s1);

		// NPC empty list (constructor purposes)
		HashMap<String, NPC> npcs = new HashMap<>();

		// Load character & inventory
		Resource wood1 = new Resource(Gender.F, "Madera de palmera", "Madera_test_1", ResourceType.WOOD);
		Resource wood2 = new Resource(Gender.F, "Madera de palmera con forma de l", "Madera_test_1", ResourceType.WOOD);
		Resource stone = new Resource(Gender.M, "Piedra", "Piedra_test", ResourceType.STONE);
		HashMap<String, Item> inventory = new HashMap<>();
		inventory.put(wood1.getName().toLowerCase(), wood1);
		inventory.put(wood2.getName().toLowerCase(), wood2);
		inventory.put(stone.getName().toLowerCase(), stone);
		player = new Player(gameManager, Gender.M, "Test", "Test_desc", inventory, "s1");
		// crossbow & blueprint
		HashMap<ResourceType, Integer> requirements = new HashMap<>();
		requirements.put(wood1.getResourceType(), 2);
		requirements.put(stone.getResourceType(), 1);
		crossbow = new Weapon(Gender.F, "Ballesta", "ballesta_desc", DamageType.SHOT, 100d);
		Blueprint crossBlueprint = new Blueprint(Gender.M, "Plano de ballesta", "Plano_desc", requirements, crossbow);

		// Add crossbow to character
		player.addItem(crossBlueprint);

		// Load game
		Game game = new Game(gameManager, player, locations, npcs, null);
		gameManager.setInternalGame(game);
	}

	@Test
	public void blueprintTest1() {
		player.use("plano de ballesta");
		Assert.assertEquals(crossbow, player.getInventory().get("ballesta"));
	}

	@Test
	public void blueprintTest2() {
		player.removeItem(player.getInventory().get("madera de palmera"));
		Assert.assertFalse(player.use("plano de ballesta"));
		Assert.assertNotEquals(crossbow, player.getInventory().get("ballesta"));
	}

}
