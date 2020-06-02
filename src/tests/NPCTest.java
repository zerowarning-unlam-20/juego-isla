package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.UserCharacter;
import items.Item;
import items.Location;
import items.Weapon;
import manager.GameManager;
import tools.DamageType;
import tools.Gender;
import tools.ItemType;
import tools.NPCType;

class NPCTest {

	@Test
	void NPCAttackTest1() {
		GameManager.setTestMode(true);
		Location arena = new Location(1, Gender.F, "arena", "test", true);
		UserCharacter player = new UserCharacter(arena); // id 0
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(50, Gender.F, "Palmera", "Palmera_test", arena, NPCType.PASSIVE);
		arena.addNpc(tree);

		player.attack(axe, tree);
		Assert.assertEquals(new Double(80d), tree.getHealth());
	}

	@Test
	void NPCAttackTest2() {
		GameManager.setTestMode(true);
		Location arena = new Location(1, Gender.F, "arena", "test", true);
		UserCharacter player = new UserCharacter(arena); // id 0
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(50, Gender.F, "Palmera", "Palmera_test_irrompible", arena, NPCType.PASSIVE);

		tree.addWeakOrRes(DamageType.HACK, 0d);
		arena.addNpc(tree);

		player.attack(axe, tree);
		Assert.assertEquals(new Double(100d), tree.getHealth());
	}

	@Test
	void NPCAttackTest3() {
		GameManager.setTestMode(true);
		Location arena = new Location(1, Gender.F, "arena", "test", true);
		UserCharacter player = new UserCharacter(arena); // id 0
		Weapon axe = new Weapon(2, Gender.M, "Hacha", "Hacha de hierro", DamageType.HACK, 20d);
		player.addItem(axe);
		NPC tree = new NPC(50, Gender.F, "Palmera", "Palmera_test_irrompible", arena, NPCType.PASSIVE);

		tree.addWeakOrRes(DamageType.HACK, 10d);
		arena.addNpc(tree);

		player.attack(axe, tree);

		Assert.assertEquals(new Double(0d), tree.getHealth());
	}

	@Test
	void NPCDropTest1() {
		GameManager.setTestMode(true);
		Location arena = new Location(1, Gender.F, "arena", "test", true);
		UserCharacter player = new UserCharacter(arena); // id 0
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

		NPC tree = new NPC(50, Gender.F, "Palmera", "Palmera_test_irrompible", arena, inventory, NPCType.PASSIVE);

		tree.addWeakOrRes(DamageType.HACK, 10d);
		arena.addNpc(tree);

		player.attack(axe, tree);

		Assert.assertEquals(expected, player.getInventory());
	}

}
