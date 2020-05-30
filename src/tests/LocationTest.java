package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.UserCharacter;
import items.Access;
import items.Location;
import manager.Game;
import manager.GameManager;
import tools.Gender;

class LocationTest {
	GameManager gameManager;
	UserCharacter character;
	Location initialLocation;

	@BeforeEach
	void load() {
		gameManager = new GameManager();
		GameManager.setTestMode(true);
		Location s1 = new Location(1, Gender.M, "s1", "Inicio", true);
		s1.addAccess(new Access(12, Gender.F, "Puerta", "Puerta azul", false, true, 2, 0));

		Location s2 = new Location(2, Gender.M, "s2", "Segundo lugar", true);
		s2.addAccess(new Access(12, Gender.F, "Puerta", "Puerta azul", false, true, 1, 0));
		s2.addAccess(new Access(23, Gender.F, "Puerta", "Puerta azul", false, true, 3, 0));
		s2.addAccess(new Access(25, Gender.F, "Puerta", "Puerta azul", false, true, 5, 0));
		s2.addAccess(new Access(26, Gender.F, "Puerta", "Puerta azul", false, true, 6, 0));

		Location s3 = new Location(3, Gender.M, "s3", "Tercer lugar", true);
		s3.addAccess(new Access(23, Gender.F, "Puerta", "Puerta azul", false, true, 2, 0));
		s3.addAccess(new Access(34, Gender.F, "Puerta", "Puerta azul", false, true, 4, 0));

		Location s4 = new Location(4, Gender.M, "s4", "Cuarto lugar", true);
		s4.addAccess(new Access(34, Gender.F, "Puerta", "Puerta azul", false, true, 3, 0));
		s4.addAccess(new Access(45, Gender.F, "Puerta", "Puerta azul", false, true, 5, 0));

		Location s5 = new Location(5, Gender.M, "s5", "Quinto lugar", true);
		s5.addAccess(new Access(45, Gender.F, "Puerta", "Puerta azul", false, true, 4, 0));
		s5.addAccess(new Access(25, Gender.F, "Puerta", "Puerta azul", false, true, 2, 0));

		Location s6 = new Location(6, Gender.M, "s6", "Sexto lugar", true);
		s6.addAccess(new Access(26, Gender.F, "Puerta", "Puerta azul", false, true, 2, 0));

		ArrayList<Location> locations = new ArrayList<>();
		locations.add(s1);
		locations.add(s2);
		locations.add(s3);
		locations.add(s4);
		locations.add(s5);
		locations.add(s5);
		locations.add(s6);

		character = new UserCharacter(s1);

		Game game = new Game(character, locations);

		GameManager gm = new GameManager();
		gm.setGame(game);

		initialLocation = s1;
	}

	@Test
	void moveTest1() {
		character.goTo(2);
		Assert.assertEquals(2, character.getLocation().getId());
	}

	@Test
	void moveTest2() {
		Assert.assertFalse(character.goTo(6));
	}

	@Test
	void moveTest3() {
		character.goTo(2);
		character.goTo(5);
		Assert.assertTrue(character.goTo(4));
		Assert.assertEquals(4, character.getLocation().getId());
	}

	@Test
	void moveTest4() {
		character.goTo(2);
		character.goTo(5);
		character.goTo(4);
		Assert.assertFalse(character.goTo(2));
		Assert.assertFalse(character.goTo(1));
	}

	@Test
	void moveTest5() {
		Assert.assertFalse(character.goTo(16));
	}

}
