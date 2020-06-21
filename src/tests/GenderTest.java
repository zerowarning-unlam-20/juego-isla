package tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Player;
import island.Location;
import manager.GameManager;
import tools.Gender;

class GenderTest {
	private GameManager gameManager;

	@BeforeEach
	public void start() {
		gameManager = new GameManager(true);
	}

	@Test
	public void GenderTest1() {
		Location testLocation = new Location(Gender.M, "s1", "Inicio", true, null, null);
		Player character = new Player(gameManager, testLocation);
		Assert.assertEquals("el Test", character.getSingularName());
		Assert.assertEquals("un Test", character.getOnlyName());
	}

	@Test
	public void GenderTest2() {
		Location testLocation = new Location(Gender.F, "Cueva", "Cueva oscura", true, null, null);
		Assert.assertEquals("la Cueva", testLocation.getSingularName());
		Assert.assertEquals("una Cueva", testLocation.getOnlyName());
	}

	@Test
	public void GenderTest3() {
		Location testLocation = new Location(Gender.F, "Cueva", "Cueva oscura", true, null, null);
		Assert.assertEquals("a", testLocation.getTermination());
		Player character = new Player(gameManager, testLocation);
		Assert.assertEquals("o", character.getTermination());
	}

}
