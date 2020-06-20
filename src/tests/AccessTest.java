package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.NPC;
import entities.UserCharacter;
import island.Location;
import items.Access;
import items.Item;
import items.types.Key;
import manager.Game;
import manager.GameManager;
import tools.Gender;

class AccessTest {

	GameManager gameManager;
	UserCharacter character;
	Location initialLocation;

	@BeforeEach
	void load() {
		gameManager = new GameManager(true);
		
		// Load locations
		Location s1 = new Location(1001, Gender.M, "s1", "Inicio", true);
		s1.addAccess(new Access(3000, Gender.F, "Puerta", "Puerta azul", false, true, 1002, 3002));
		Location s2 = new Location(1002, Gender.M, "s2", "Segundo lugar", true);
		s2.addAccess(new Access(3001, Gender.F, "Puerta", "Puerta azul", false, true, 1001, 0));
		ArrayList<Location> locations = new ArrayList<>();
		locations.add(s1);
		locations.add(s2);
		
		// Load empty NPC list
		ArrayList<NPC> npcs = new ArrayList<>();
		
		// Load character
		character = new UserCharacter(gameManager, s1);

		// Load game into manager
		Game game = new Game(gameManager, character, locations, npcs);

		gameManager.setInternalGame(game);

		initialLocation = s1;
	}

	@Test
	public void accessTest1() {
		Item key = new Key(3002, Gender.F, "Llave", "Llave_test");
		character.addItem(key);
		Assert.assertTrue(character.goTo(1002));
	}

}
