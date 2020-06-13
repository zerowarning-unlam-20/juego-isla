package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.UserCharacter;
import island.Access;
import island.Location;
import manager.Game;
import manager.GameManager;
import tools.Gender;

class CommandTest {
	GameManager gameManager;
	Game game;
	UserCharacter user;
	
	@BeforeEach
	void setUp() throws Exception {
		Location north = new Location(1, Gender.M, "norte", "norte", true);
		north.addAccess(new Access(12, Gender.F, "Puerta", "Puerta azul", false, true, 1, 0));
		north.addAccess(new Access(12, Gender.F, "Puerta", "Puerta azul", false, true, 1, 0));
		Location south = new Location(1, Gender.M, "sur", "sur", true);
		Location east = new Location(1, Gender.M, "este", "este", true);
		Location west = new Location(1, Gender.M, "oeste", "oeste", true);
		Location cave = new Location(1, Gender.F, "cueva", "cueva", true);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
