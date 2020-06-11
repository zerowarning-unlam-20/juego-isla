package tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.UserCharacter;
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
		Location testLocation = new Location(1, Gender.M, "s1", "Inicio", true);
		UserCharacter character = new UserCharacter(gameManager, testLocation);
		Assert.assertEquals("el Test", character.getSingularName());
		Assert.assertEquals("un Test", character.getOnlyName());
	}

	@Test
	public void GenderTest2() {
		Location testLocation = new Location(1, Gender.F, "Cueva", "Cueva oscura", true);
		Assert.assertEquals("la Cueva", testLocation.getSingularName());
		Assert.assertEquals("una Cueva", testLocation.getOnlyName());
	}

	@Test
	public void GenderTest3() {
		Location testLocation = new Location(1, Gender.F, "Cueva", "Cueva oscura", true);
		Assert.assertEquals("a", testLocation.getTermination());
		UserCharacter character = new UserCharacter(gameManager, testLocation);
		Assert.assertEquals("o", character.getTermination());
	}

}
