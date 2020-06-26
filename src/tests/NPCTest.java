package tests;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.Player;
import island.Location;
import items.Access;
import items.Item;
import items.types.Food;
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
	Location s1;
	Player player;

	@BeforeEach
	void load() {
		gameManager = new GameManager(true);
		// Load locations
		s1 = new Location(Gender.M, "s1", "Inicio", true, new HashMap<>(), new HashMap<>());
		Location s2 = new Location(Gender.M, "s2", "Inicio", true, new HashMap<>(), new HashMap<>());
		// Load accesses
		s1.addAccess(new Access(Gender.F, "Puerta", "Puerta", false, true, null, "s2", null, DamageType.BLUNT));
		HashMap<String, Location> locations = new HashMap<>();
		locations.put(s1.getName().toLowerCase(), s1);
		locations.put(s2.getName().toLowerCase(), s2);

		// Load player
		player = new Player(gameManager, s1);

		// Load game
		Game game = new Game(gameManager, player, locations, new HashMap<>(), null);
		gameManager.setInternalGame(game);
	}

	@Test
	void NPCAttackTest1() { // Checks lowered health
		Weapon axe = new Weapon(Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(gameManager, Gender.F, "Palmera", "test", s1, new HashMap<>(), "s1", NPCType.INANIMATED,
				null, null);
		s1.addEntity(tree);

		player.attack("hacha", "palmera");
		Assert.assertEquals(new Double(80d), tree.getHealth());
	}

	@Test
	void NPCAttackTest2() { // Checks null damage
		Weapon axe = new Weapon(Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(gameManager, Gender.F, "Palmera", "test", s1, new HashMap<>(), "s1", NPCType.INANIMATED,
				null, null);

		tree.addWeakOrRes(DamageType.HACK, 0d);
		s1.addEntity(tree);

		player.attack("hacha", "palmera");
		Assert.assertEquals(new Double(100d), tree.getHealth());
	}

	@Test
	void NPCAttackTest3() { // Checks full damage and dead NPC
		Weapon axe = new Weapon(Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(gameManager, Gender.F, "Palmera", "test", s1, new HashMap<>(), "s1", NPCType.INANIMATED,
				new HashMap<>(), null);

		tree.addWeakOrRes(DamageType.HACK, 10d);
		s1.addEntity(tree);

		player.attack("hacha", "palmera");

		Assert.assertEquals(new Double(0d), tree.getHealth());
		Assert.assertEquals(Dead.class, tree.getState().getClass());
	}

	@Test
	void NPCDropTest1() { // NPC drop test - fullDrop
		Weapon axe = new Weapon(Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		Resource wood1 = new Resource(Gender.F, "Madera", "Madera_test_1", ResourceType.WOOD);
		Resource wood2 = new Resource(Gender.F, "Madera", "Madera_test_1", ResourceType.WOOD);
		Food coconut = new Food(Gender.M, "Coco", "Coco_test", null);

		HashMap<String, Item> inventory = new HashMap<>();
		inventory.put(wood1.getName().toLowerCase(), wood1);
		inventory.put(wood2.getName().toLowerCase(), wood2);
		inventory.put(coconut.getName().toLowerCase(), coconut);

		HashMap<String, Item> expected = new HashMap<>();
		expected.put(axe.getName().toLowerCase(), axe);
		expected.put(wood1.getName().toLowerCase(), wood1);
		expected.put(wood2.getName().toLowerCase(), wood2);
		expected.put(coconut.getName().toLowerCase(), coconut);

		NPC tree = new NPC(gameManager, Gender.M, "Palmera", "test", s1, inventory, "s1", NPCType.INANIMATED,
				new HashMap<>(), null);

		tree.addWeakOrRes(DamageType.HACK, 10d);
		s1.addEntity(tree);

		player.attack("hacha", "palmera");

		Assert.assertEquals(expected, player.getInventory());
	}

	@Test
	void NPCAccessTest() { // Access opened when NPC dies
		Player player = new Player(gameManager, s1);
		Weapon axe = new Weapon(Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);

		NPC parkingMeter = new NPC(gameManager, Gender.M, "Parquimetro", "test", s1, new HashMap<>(), "s1",
				NPCType.INANIMATED, new HashMap<>(), null);
		parkingMeter.addWeakOrRes(DamageType.HACK, 10d);
		s1.addEntity(parkingMeter);

		player.attack("hacha", "parquimetro");
		Assert.assertEquals(false, player.getLocation().getAccesses().get("s2").isLocked());
	}

	@Test
	public void talkTest1() { // Talk to an NPC
		HashMap<String, String> chat = new HashMap<>();
		chat.put("escapar", "Arma una canoa o algo y escapa");
		chat.put("recursos", "Busca algo que sirva como arma");
		chat.put("デフォールト", "Perdon, no te entendi");
		NPC parkingMeter = new NPC(gameManager, Gender.M, "Parquimetro", "test", s1, new HashMap<>(), "s1",
				NPCType.PASSIVE, chat, null);
		s1.addEntity(parkingMeter);
		Assert.assertTrue(player.talk("parquimetro", "escapar"));
		Assert.assertTrue(player.talk("parquimetro", "recursos"));
		Assert.assertFalse(player.talk("parquimetro", "lol"));
	}

	@Test
	public void talkTest2() { // Talk to an NPC, this wont understand
		HashMap<String, String> chat = new HashMap<>();
		chat.put("デフォールト", "Perdon, no te entendi");
		NPC parkingMeter = new NPC(gameManager, Gender.M, "Parquimetro", "test", s1, new HashMap<>(), "s1",
				NPCType.INANIMATED, new HashMap<>(), null);
		s1.addEntity(parkingMeter);
		Assert.assertFalse(player.talk("parquimetro", "escapar"));
		Assert.assertFalse(player.talk("parquimetro", "recursos"));
		Assert.assertFalse(player.talk("parquimetro", "lol"));
	}

}
