package tests;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.UserCharacter;
import items.Blueprint;
import items.Item;
import items.Weapon;
import manager.GameManager;
import tools.DamageType;
import tools.Gender;
import tools.ItemType;

class BlueprintTest {
	private GameManager gameManager;

	@BeforeEach
	public void load() {
		gameManager = new GameManager(true);
	}

	@Test
	public void blueprintTest1() {
		Item wood1 = new Item(56, Gender.F, "Madera", "Madera_test_1", ItemType.RESOURCE);
		Item wood2 = new Item(56, Gender.F, "Madera", "Madera_test_1", ItemType.RESOURCE);
		Item stone = new Item(57, Gender.M, "Piedra", "Piedra_test", ItemType.RESOURCE);
		ArrayList<Item> inventory = new ArrayList<>();
		inventory.add(wood1);
		inventory.add(wood2);
		inventory.add(stone);
		UserCharacter player = new UserCharacter(gameManager, 0, Gender.M, "Test", "Test_desc", null, inventory); // id
																													// 0
		HashMap<String, Integer> map = new HashMap<>();
		map.put(wood1.getName(), 2);
		map.put(stone.getName(), 1);

		Weapon crossbow = new Weapon(99, Gender.F, "Ballesta", "ballesta_desc", DamageType.SHOT, 100d);
		Blueprint crossBlueprint = new Blueprint(22, Gender.M, "Plano ballesta", "Plano_desc", ItemType.BLUEPRINT, map,
				crossbow);
		player.addItem(crossBlueprint);
		player.use(crossBlueprint);

		Assert.assertEquals(crossbow, player.getInventory().get(1));
	}

	@Test
	public void blueprintTest2() {
		Item wood1 = new Item(56, Gender.F, "Madera", "Madera_test_1", ItemType.RESOURCE);
		Item wood2 = new Item(56, Gender.F, "Madera", "Madera_test_1", ItemType.RESOURCE);
		Item stone = new Item(57, Gender.M, "Piedra", "Piedra_test", ItemType.RESOURCE);
		ArrayList<Item> inventory = new ArrayList<>();
		inventory.add(wood1);
		inventory.add(wood2);
		inventory.add(stone);
		UserCharacter player = new UserCharacter(gameManager, 0, Gender.M, "Test", "Test_desc", null, inventory); // id
																													// 0

		HashMap<String, Integer> map = new HashMap<>();
		map.put(wood1.getName(), 2);
		map.put(stone.getName(), 1);

		Weapon crossbow = new Weapon(99, Gender.F, "Ballesta", "ballesta_desc", DamageType.SHOT, 100d);
		Blueprint crossBlueprint = new Blueprint(22, Gender.M, "Plano catapulta", "Plano_desc", ItemType.BLUEPRINT, map,
				crossbow);
		player.addItem(crossBlueprint);

		Assert.assertNotEquals(crossbow, player.getInventory().get(1));
	}

}
