package tests;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.UserCharacter;
import island.Location;
import items.Access;
import items.types.Text;
import manager.Game;
import manager.GameManager;
import tools.Gender;

class BookTest {
	UserCharacter character;
	GameManager gameManager;

	@BeforeEach
	public void start() {
		gameManager = new GameManager(true);

		// Load locations
		Location cave = new Location(1, Gender.M, "s1", "Inicio", false);
		Location s1 = new Location(2, Gender.M, "s1", "Inicio", true);

		ArrayList<Location> locations = new ArrayList<>();
		locations.add(cave);
		locations.add(s1);

		// Load accesses
		Access exit = new Access(3, Gender.F, "Salida", "salida de cueva", false, true, 2, 0);
		cave.addAccess(exit);

		// Load character
		character = new UserCharacter(gameManager, cave);

		// Load empty NPC list
		ArrayList<NPC> npcs = new ArrayList<>();

		// Load game
		Game game = new Game(gameManager, character, locations, npcs);
		gameManager.setInternalGame(game);
	}

	@Test
	public void bookTest1() {
		Text note = new Text(4, Gender.F, "Hoja", "Hoja del viajero", "Se necesitan troncos y "
				+ "lianas para poder armar la canoa. " + "Tambien llevar provisiones (IMPORTANTE COMIDA) y un remo");
		character.addItem(note);
		Assert.assertFalse(character.look(note));
		character.removeItem(4);
	}

	@Test
	public void bookTest2() {
		Text note = new Text(4, Gender.F, "Hoja", "Hoja del viajero", "Se necesitan troncos y "
				+ "lianas para poder armar la canoa. " + "Tambien llevar provisiones (IMPORTANTE COMIDA) y un remo");
		character.addItem(note);
		character.goTo(1);
		Assert.assertFalse(character.look(note));
		character.removeItem(4);
	}

	@After
	public void afterTest() {
		character.removeItem(4);
	}

	@Test
	public void bookTest3() {
		Text note = new Text(4, Gender.F, "Hoja", "Hoja del viajero", "Se necesitan troncos y "
				+ "lianas para poder armar la canoa. " + "Tambien llevar provisiones (IMPORTANTE COMIDA) y un remo");
		character.getLocation().addItem(note);
		character.grab(note);
		Assert.assertFalse(character.look(note)); // Adentro de la cueva no se puede leer
		character.removeItem(4);
	}

	@Test
	public void bookTest4() {
		Text note = new Text(4, Gender.F, "Hoja", "Hoja del viajero", "Se necesitan troncos y "
				+ "lianas para poder armar la canoa. " + "Tambien llevar provisiones (IMPORTANTE COMIDA) y un remo");
		character.grab(note); // No esta la note en la cueva
		character.goTo(1); // Sigo adentro de la cueva
		Assert.assertFalse(character.look(note));
		character.removeItem(4);
	}

	@Test
	public void bookTest5() {
		Text note = new Text(4, Gender.F, "Hoja", "Hoja del viajero", "Se necesitan troncos y "
				+ "lianas para poder armar la canoa. " + "Tambien llevar provisiones (IMPORTANTE COMIDA) y un remo");
		character.getLocation().addItem(note); // agrego el objeto a la cueva
		character.goTo(2);
		Assert.assertFalse(character.grab(note));// estoy afuera de la cueva. no podria poder agregarla
		character.removeItem(4);
	}

	@Test
	public void bookTest6() {
		Text note = new Text(4, Gender.F, "Hoja", "Hoja del viajero", "Se necesitan troncos y "
				+ "lianas para poder armar la canoa. " + "Tambien llevar provisiones (IMPORTANTE COMIDA) y un remo");
		character.getLocation().addItem(note); // agrego el objeto a la cueva
		character.grab(note); // la agarro debidamente
		character.goTo(2);
		Assert.assertTrue(character.look(note)); // estoy afuera de la cueva y con el objeto correctamente
		character.removeItem(4);
	}

}
