package tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import island.Location;
import items.types.Bottle;
import items.types.Weapon;
import manager.GameManager;
import states.Lost;

class CommandTest {
	GameManager game;

	@BeforeEach
	public void load() {
		game = new GameManager(true);
		game.loadGame("Blue Hawaii");
	}

	@Test
	public void testGoto() {
		game.sendCommand("agarrar brujula");
		game.sendCommand("usar brujula");
		game.sendCommand("ir oeste");
		Location location = game.getGame().getCharacter().getLocation();
		Assert.assertEquals("Oeste", location.getName());
	}

	@Test
	public void testBrujula() {
		game.sendCommand("agarrar brujula");
		game.sendCommand("usar brujula");
		Assert.assertNotNull(game.getGame().getCharacter().getInventory().get("brujula"));
		Lost lost = (Lost) game.getGame().getCharacter().getState();
		Assert.assertEquals(false, lost.getCompletelyLost());
	}

	@Test
	public void testPlanoRoto() {
		game.sendCommand("agarrar brujula");
		game.sendCommand("usar brujula");
		Assert.assertNotNull(game.getGame().getCharacter().getInventory().get("brujula"));
		game.sendCommand("ir oeste");
		game.sendCommand("agarrar hacha");
		game.sendCommand("ir sur");
		game.sendCommand("golpear mono con hacha");
		game.sendCommand("golpear mono con hacha");
		game.sendCommand("golpear mono con hacha");
		game.sendCommand("golpear mono con hacha");
		game.sendCommand("golpear liana con cuchillo oxidado");
		game.sendCommand("golpear liana con cuchillo oxidado");
		game.sendCommand("golpear liana con cuchillo oxidado");
		game.sendCommand("golpear liana con cuchillo oxidado");
		Assert.assertNotNull(game.getGame().getCharacter().getInventory().get("liana cortada"));
		game.sendCommand("ir este");
		game.sendCommand("golpear palmera con hacha");
		game.sendCommand("golpear palmera con hacha");
		game.sendCommand("golpear palmera con hacha");
		game.sendCommand("golpear palmera con hacha");
		game.sendCommand("ir cueva");
		game.sendCommand("golpear cofre con hacha");
		game.sendCommand("inspeccionar cofre");
		game.sendCommand("usar plano");
		game.sendCommand("usar balsa");
		Assert.assertNotNull(game.getGame().getCharacter().getInventory().get("balsa"));
		game.sendCommand("ir este");
		game.sendCommand("ir sur");
		game.sendCommand("ir mar");
		game.sendCommand("atacar tiburon con mano");
		Assert.assertTrue(game.isGameOver());
	}

	@Test
	public void testOutOfPlace() {
		game.sendCommand("golpear mono con hacha");
		game.sendCommand("golpear mono con hacha");
		game.sendCommand("golpear mono con hacha");
		game.sendCommand("golpear mono con hacha");
		Assert.assertNull(game.getGame().getCharacter().getInventory().get("cuchillo oxidado"));
	}

	@Test
	public void testWhat() {
		game.sendCommand("dalsidjwoidjawiodjawiodjawiodja");
		Assert.assertEquals("Que?", game.getMessageHistory().get(game.getMessageHistory().size() - 1).getContent());
	}

	@Test
	public void testGrab() {
		game.sendCommand("agarrar brujula");
		game.sendCommand("usar brujula");
		game.sendCommand("ir oeste");
		game.sendCommand("agarrar hacha");
		Weapon axe = (Weapon) game.getGame().getCharacter().getInventory().get("hacha");
		Assert.assertNotNull(axe);
	}

	@Test
	public void testGrabBottle() {
		game.sendCommand("agarrar botella");
		game.sendCommand("agarrar agua de catarata con botella");
		Bottle botella = (Bottle) game.getGame().getCharacter().getInventory().get("botella");
		Assert.assertNotNull(botella.getContent());
	}

}
