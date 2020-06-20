package tests;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.UserCharacter;
import island.Location;
import items.Access;
import items.Item;
import items.types.Consumable;
import items.types.Resource;
import items.types.Weapon;
import manager.Game;
import manager.GameManager;
import states.Dead;
import tools.DamageType;
import tools.Gender;
import tools.NPCType;
import tools.ResourceType;

class NPCTest {
	GameManager gameManager;
	Location arena;
	UserCharacter player;

	@BeforeEach
	void load() {
		gameManager = new GameManager(true);
		// Load locations
		arena = new Location(1, Gender.F, "arena", "test", true);
		ArrayList<Location> locations = new ArrayList<>();
		locations.add(arena);
		player = new UserCharacter(gameManager, arena); // id 0

		Game game = new Game(gameManager, player, locations, new ArrayList<NPC>());
		gameManager.setInternalGame(game);
	}

	@Test
	void NPCAttackTest1() { // Checks lowered health
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(gameManager, 50, Gender.F, "Palmera", "Palmera_test", arena, NPCType.PASSIVE, 0);
		arena.addNpc(tree);

		player.attack(axe, tree);
		Assert.assertEquals(new Double(80d), tree.getHealth());
	}

	@Test
	void NPCAttackTest2() { // Checks null damage
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(gameManager, 50, Gender.F, "Palmera", "Palmera_test_irrompible", arena, NPCType.PASSIVE, 0);

		tree.addWeakOrRes(DamageType.HACK, 0d);
		arena.addNpc(tree);

		player.attack(axe, tree);
		Assert.assertEquals(new Double(100d), tree.getHealth());
	}

	@Test
	void NPCAttackTest3() { // Checks full damage and dead NPC
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(gameManager, 50, Gender.F, "Palmera", "Palmera_test_irrompible", arena, NPCType.PASSIVE, 0);

		tree.addWeakOrRes(DamageType.HACK, 10d);
		arena.addNpc(tree);

		player.attack(axe, tree);

		Assert.assertEquals(new Double(0d), tree.getHealth());
		Assert.assertEquals(Dead.class, tree.getState().getClass());
	}

	@Test
	void NPCDropTest1() { // NPC drop test - fullDrop
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		Resource wood1 = new Resource(56, Gender.F, "Madera", "Madera_test_1", ResourceType.WOOD);
		Resource wood2 = new Resource(56, Gender.F, "Madera", "Madera_test_1", ResourceType.WOOD);
		Consumable coconut = new Consumable(57, Gender.M, "Coco", "Coco_test", false, null);

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
	void NPCAccessTest() { // Access opened when NPC dies

		Access access = new Access(99, Gender.F, "Puerta", "Puerta_desc", true, false, 1, 0);
		arena.addAccess(access);

		UserCharacter player = new UserCharacter(gameManager, arena); // id 0
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);

		ArrayList<Item> inventory = new ArrayList<>();

		NPC tree = new NPC(gameManager, 50, Gender.F, "Piedra", "Piedra_test_desc", arena, inventory, NPCType.PASSIVE,
				1, arena.getId());

		tree.addWeakOrRes(DamageType.HACK, 10d);
		arena.addNpc(tree);

		player.attack(axe, tree);

		Assert.assertEquals(false, access.isLocked());
	}

	@Test
	public void talkTest1() { // Talk to an NPC
		HashMap<String, String> chat = new HashMap<>();
		chat.put("escapar", "Arma una canoa o algo y escapa");
		chat.put("recursos", "Busca algo que sirva como arma");
		chat.put("デフォールト", "Perdon, no te entendi");
		NPC parkingMeter = new NPC(gameManager, 1, Gender.M, "Parquimetro", "parking_meter_test", null, NPCType.PASSIVE,
				0, chat);
		Assert.assertTrue(player.talk(parkingMeter, "escapar"));
		Assert.assertTrue(player.talk(parkingMeter, "recursos"));
		Assert.assertFalse(player.talk(parkingMeter, "popo"));
	}

	@Test
	public void talkTest2() { // Talk to an NPC, this wont understand
		gameManager.setTestMode(false);
		HashMap<String, String> chat = new HashMap<>();
		chat.put("デフォールト", "Perdon, no te entendi");
		NPC parkingMeter = new NPC(gameManager, 1, Gender.M, "Parquimetro", "parking_meter_test", null, NPCType.PASSIVE,
				0, chat);
		Assert.assertFalse(player.talk(parkingMeter, "escapar"));
		Assert.assertFalse(player.talk(parkingMeter, "recursos"));
		Assert.assertFalse(player.talk(parkingMeter, "popo"));
		gameManager.setTestMode(true);
	}

}
