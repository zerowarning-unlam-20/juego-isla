package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.UserCharacter;
import items.Access;
import items.Location;
import items.Text;
import manager.Game;
import manager.GameManager;
import tools.Gender;
import tools.ItemType;

class BookTest {
	UserCharacter character;
	GameManager gameManager;

	@BeforeEach
	public void start() {
		GameManager.setTestMode(true);
		gameManager = new GameManager();
		Location cave = new Location(1, Gender.M, "s1", "Inicio", false);
		Location s1 = new Location(2, Gender.M, "s1", "Inicio", true);
		Access exit = new Access(3, Gender.F, "Salida", "salida de cueva", false, true, 2, 0);
		cave.addAccess(exit);
		character = new UserCharacter(cave);
		ArrayList<Location> locations = new ArrayList<>();
		locations.add(cave);
		locations.add(s1);
		Game game = new Game(character, locations);
		gameManager.setGame(game);
	}

	@Test
	public void bookTest1() {
		Text note = new Text(4, Gender.F, "Hoja", "Hoja del viajero", true, ItemType.UNBREAKABLE,
				"Se necesitan troncos y " + "lianas para poder armar la canoa. "
						+ "Tambien llevar provisiones (IMPORTANTE COMIDA) y un remo");
		character.addItem(note);
		Assert.assertFalse(character.look(note));
		character.removeItem(4);
	}

	@Test
	public void bookTest2() {
		Text note = new Text(4, Gender.F, "Hoja", "Hoja del viajero", true, ItemType.UNBREAKABLE,
				"Se necesitan troncos y " + "lianas para poder armar la canoa. "
						+ "Tambien llevar provisiones (IMPORTANTE COMIDA) y un remo");
		character.addItem(note);
		character.goTo(1);
		Assert.assertFalse(character.look(note));
		character.removeItem(4);
	}

	@After
	public void afterTest() {
		character.removeItem(4);
	}
}
