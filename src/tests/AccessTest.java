package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.UserCharacter;
import island.Location;
import items.Access;
import items.Item;
import manager.Game;
import manager.GameManager;
import tools.Gender;
import tools.ItemType;

class AccessTest {

	GameManager gameManager;
	UserCharacter character;
	Location initialLocation;

	@BeforeEach
	void load() {
		gameManager = new GameManager(true); // Test mode
		Location s1 = new Location(1, Gender.M, "s1", "Inicio", true);
		s1.addAccess(new Access(12, Gender.F, "Puerta", "Puerta azul", false, true, 2, 33));

		Location s2 = new Location(2, Gender.M, "s2", "Segundo lugar", true);
		s2.addAccess(new Access(21, Gender.F, "Puerta", "Puerta azul", false, true, 1, 0));

		ArrayList<Location> locations = new ArrayList<>();
		locations.add(s1);
		locations.add(s2);

		character = new UserCharacter(gameManager, s1);

		Game game = new Game(character, locations);

		gameManager.setGame(game);

		initialLocation = s1;
	}

	@Test
	public void accessTest1() {
		Item key = new Item(33, Gender.F, "Llave", "Llave_test", ItemType.KEY);
		character.addItem(key);
		Assert.assertTrue(character.goTo(2));
	}

}
