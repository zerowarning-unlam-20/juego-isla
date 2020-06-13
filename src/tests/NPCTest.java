package tests;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.UserCharacter;
import island.Access;
import island.Location;
import items.Item;
import items.Weapon;
import manager.GameManager;
import tools.DamageType;
import tools.Gender;
import tools.ItemType;
import tools.NPCType;

class NPCTest {
	GameManager gameManager;

	@BeforeEach
	void load() {
		gameManager = new GameManager(true);
	}

	@Test
	void NPCAttackTest1() {
		Location arena = new Location(1, Gender.F, "arena", "test", true);
		UserCharacter player = new UserCharacter(gameManager, arena); // id 0
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(gameManager, 50, Gender.F, "Palmera", "Palmera_test", arena, NPCType.PASSIVE, 0);
		arena.addNpc(tree);

		player.attack(axe, tree);
		Assert.assertEquals(new Double(80d), tree.getHealth());
	}

	@Test
	void NPCAttackTest2() {
		Location arena = new Location(1, Gender.F, "arena", "test", true);
		UserCharacter player = new UserCharacter(gameManager, arena); // id 0
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(gameManager, 50, Gender.F, "Palmera", "Palmera_test_irrompible", arena, NPCType.PASSIVE, 0);

		tree.addWeakOrRes(DamageType.HACK, 0d);
		arena.addNpc(tree);

		player.attack(axe, tree);
		Assert.assertEquals(new Double(100d), tree.getHealth());
	}

	@Test
	void NPCAttackTest3() {
		Location arena = new Location(1, Gender.F, "arena", "test", true);
		UserCharacter player = new UserCharacter(gameManager, arena); // id 0
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(gameManager, 50, Gender.F, "Palmera", "Palmera_test_irrompible", arena, NPCType.PASSIVE, 0);

		tree.addWeakOrRes(DamageType.HACK, 10d);
		arena.addNpc(tree);

		player.attack(axe, tree);

		Assert.assertEquals(new Double(0d), tree.getHealth());
	}

	@Test
	void NPCDropTest1() {
		Location arena = new Location(1, Gender.F, "arena", "test", true);
		UserCharacter player = new UserCharacter(gameManager, arena); // id 0
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		Item wood1 = new Item(56, Gender.F, "Madera", "Madera_test_1", ItemType.RESOURCE);
		Item wood2 = new Item(56, Gender.F, "Madera", "Madera_test_1", ItemType.RESOURCE);
		Item coconut = new Item(57, Gender.M, "Coco", "Coco_test", ItemType.EDIBLE);

		ArrayList<Item> inventory = new ArrayList<>();
		inventory.add(wood1);
		inventory.add(wood2);
		inventory.add(coconut);

		ArrayList<Item> expected = new ArrayList<>();
		expected.add(axe);
		expected.add(wood1);
		expected.add(wood2);
		expected.add(coconut);

		NPC tree = new NPC(gameManager, 50, Gender.F, "Palmera", "Palmera_test_irrompible", arena, inventory,
				NPCType.PASSIVE, 0, arena.getId());

		tree.addWeakOrRes(DamageType.HACK, 10d);
		arena.addNpc(tree);

		player.attack(axe, tree);

		Assert.assertEquals(expected, player.getInventory());
	}

	@Test
	void NPCAccessTest() { // Un arbol tapando el camino
		Location arena = new Location(1, Gender.F, "arena", "test", true);
		Access access = new Access(99, Gender.F, "Puerta", "Puerta_desc", true, false, 1, 0);
		arena.addAccess(access);

		UserCharacter player = new UserCharacter(gameManager, arena); // id 0
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);

		ArrayList<Item> inventory = new ArrayList<>();

		NPC tree = new NPC(gameManager, 50, Gender.F, "Piedra", "Piedra_test_desc", arena, inventory, NPCType.PASSIVE,
				1,arena.getId());

		tree.addWeakOrRes(DamageType.HACK, 10d);
		arena.addNpc(tree);

		player.attack(axe, tree);

		Assert.assertEquals(false, access.isLocked());
	}

	@Test
	public void talkTest1() {
		UserCharacter player = new UserCharacter(gameManager, null); // id 0
		HashMap<String, String> chat = new HashMap<>();
		chat.put("escapar", "Arma una canoa o algo y escapa");
		chat.put("recursos", "Busca algo que sirva como arma");
		NPC parkingMeter = new NPC(gameManager, 1, Gender.M, "Parquimetro", "parking_meter_test", null, NPCType.PASSIVE,
				0, chat);
		Assert.assertTrue(player.talk(parkingMeter, "escapar"));
		Assert.assertTrue(player.talk(parkingMeter, "recursos"));
		Assert.assertFalse(player.talk(parkingMeter, "popo"));
	}

	@Test
	public void talkTest2() {
		UserCharacter player = new UserCharacter(gameManager, null); // id 0
		NPC parkingMeter = new NPC(gameManager, 1, Gender.F, "Pelota", "pelota_test", null, NPCType.INANIMATED, 0);
		Assert.assertFalse(player.talk(parkingMeter, "escapar"));
		Assert.assertFalse(player.talk(parkingMeter, "recursos"));
		Assert.assertFalse(player.talk(parkingMeter, "popo"));
	}

}
