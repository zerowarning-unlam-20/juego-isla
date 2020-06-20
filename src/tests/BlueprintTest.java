package tests;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.UserCharacter;
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

	@BeforeEach
	public void load() {
		gameManager = new GameManager(true);
	}

	@Test
	public void blueprintTest1() {
		// Load environment
		Location s1 = new Location(1000, Gender.M, "s1", "Inicio", true);
		ArrayList<Location> locations = new ArrayList<>();
		locations.add(s1);

		// NPC empty list (constructor purposes)
		ArrayList<NPC> npcs = new ArrayList<>();

		// Load character & inventory
		Resource wood1 = new Resource(3001, Gender.F, "Madera", "Madera_test_1", ResourceType.WOOD);
		Resource wood2 = new Resource(3002, Gender.F, "Madera", "Madera_test_1", ResourceType.WOOD);
		Resource stone = new Resource(3003, Gender.M, "Piedra", "Piedra_test", ResourceType.STONE);
		ArrayList<Item> inventory = new ArrayList<>();
		inventory.add(wood1);
		inventory.add(wood2);
		inventory.add(stone);
		UserCharacter player = new UserCharacter(gameManager, 1, Gender.M, "Test", "Test_desc", inventory, 1000);

		// crossbow & blueprint
		HashMap<ResourceType, Integer> requirements = new HashMap<>();
		requirements.put(wood1.getResourceType(), 2);
		requirements.put(stone.getResourceType(), 1);
		Weapon crossbow = new Weapon(3000, Gender.F, "Ballesta", "ballesta_desc", DamageType.SHOT, 100d);
		Blueprint crossBlueprint = new Blueprint(3004, Gender.M, "Plano de ballesta", "Plano_desc", requirements,
				crossbow);

		// Add crossbow to character
		inventory.add(crossBlueprint);

		// Load game
		Game game = new Game(gameManager, player, locations, npcs);
		gameManager.setInternalGame(game);

		player.use(crossBlueprint);
		Assert.assertEquals(crossbow, player.getInventory().get(0));
	}

	@Test
	public void blueprintTest2() {
		// Load environment
		Location s1 = new Location(1000, Gender.M, "s1", "Inicio", true);
		ArrayList<Location> locations = new ArrayList<>();
		locations.add(s1);

		// NPC empty list (constructor purposes)
		ArrayList<NPC> npcs = new ArrayList<>();

		// Load character & inventory
		Resource wood1 = new Resource(3001, Gender.F, "Madera", "Madera_test_1", ResourceType.WOOD);
		Resource wood2 = new Resource(3002, Gender.F, "Madera", "Madera_test_1", ResourceType.WOOD);
		Resource stone = new Resource(3003, Gender.M, "Piedra", "Piedra_test", ResourceType.STONE);
		ArrayList<Item> inventory = new ArrayList<>();
		inventory.add(wood1);
		inventory.add(wood2);
		inventory.add(stone);
		UserCharacter player = new UserCharacter(gameManager, 1, Gender.M, "Test", "Test_desc", inventory, 1000);

		// crossbow & blueprint
		HashMap<ResourceType, Integer> requirements = new HashMap<>();
		requirements.put(wood1.getResourceType(), 2);
		requirements.put(stone.getResourceType(), 1);
		Weapon crossbow = new Weapon(3000, Gender.F, "Ballesta", "ballesta_desc", DamageType.SHOT, 100d);
		Blueprint crossBlueprint = new Blueprint(3004, Gender.M, "Plano de ballesta", "Plano_desc", requirements,
				crossbow);

		// Add crossbow to character
		inventory.add(crossBlueprint);

		// Load game
		Game game = new Game(gameManager, player, locations, npcs);
		gameManager.setInternalGame(game);

		player.removeItem(3002);
		Assert.assertFalse(player.use(crossBlueprint));
		Assert.assertNotEquals(crossbow, player.getInventory().get(0));
	}

}
