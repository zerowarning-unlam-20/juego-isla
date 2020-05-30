package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.UserCharacter;
import items.Liquid;
import items.Location;
import items.SingleContainer;
import tools.Gender;
import tools.ItemType;

class SingleContainerTest {
	@Test
	public void bottleTest1() {
		SingleContainer bottle = new SingleContainer(2, Gender.F, "Botella", "Botella de vidrio", ItemType.UNBREAKABLE);
		Assert.assertEquals(null, bottle.getContent());
	}

	@Test
	public void bottleTest2() {
		SingleContainer bottle = new SingleContainer(2, Gender.F, "Botella", "Botella de vidrio", ItemType.UNBREAKABLE);
		Liquid water = new Liquid(4, Gender.F, "agua", "Agua de catarata", true);
		bottle.setContent(water);
		Assert.assertEquals(bottle.getContent(), water);
	}

	@Test
	public void bottleTest3() {
		Location s1 = new Location(1, Gender.M, "s1", "Inicio", true);
		SingleContainer bottle = new SingleContainer(2, Gender.F, "Botella", "Botella de vidrio", ItemType.UNBREAKABLE);
		Liquid water = new Liquid(4, Gender.F, "agua", "Agua de catarata", true);
		SingleContainer waterfall = new SingleContainer(3, Gender.F, "Cataratas", "Cataratas con mucha agua", water,
				true, ItemType.UNBREAKABLE, true);
		UserCharacter p = new UserCharacter(s1);
		s1.addItem(bottle);
		p.grab(bottle);
		p.grab(waterfall.getContent());
		Assert.assertEquals(((SingleContainer) p.getInventory().get(0)).getContent(), water);
	}

	@Test
	public void bottleTest4() {
		Location s1 = new Location(1, Gender.M, "s1", "Inicio", true);
		SingleContainer bottle = new SingleContainer(2, Gender.F, "Botella", "Botella de vidrio", ItemType.UNBREAKABLE);
		Liquid water = new Liquid(4, Gender.F, "agua", "Agua de catarata", true);
		SingleContainer waterfall = new SingleContainer(3, Gender.F, "Cataratas", "Cataratas con mucha agua", water,
				true, ItemType.UNBREAKABLE, true);
		UserCharacter p = new UserCharacter(s1);
		p.grab(bottle);
		p.grab(waterfall.getContent());
		Assert.assertFalse(p.grab(bottle));
	}

}
