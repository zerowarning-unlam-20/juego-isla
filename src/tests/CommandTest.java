package tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manager.GameManager;

class CommandTest {
	GameManager game;

	@BeforeEach
	public void load() {
		game = new GameManager(true);
		game.loadGame("testGame");
	}
	/*
	 * @Test public void testGoto() { game.sendCommand("agarrar brujula");
	 * game.sendCommand("usar brujula"); game.sendCommand("ir oeste"); Location
	 * location = game.getGame().getCharacter().getLocation();
	 * Assert.assertEquals("Oeste", location.getName()); }
	 */

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
		game.sendCommand("golpear cofre con hacha");
		game.sendCommand("inspeccionar cofre");
		game.sendCommand("golpear palmera con hacha");
		game.sendCommand("golpear palmera con hacha");
		game.sendCommand("golpear palmera con hacha");
		game.sendCommand("golpear palmera con hacha");
		game.sendCommand("usar plano");
		Assert.assertNotNull(game.getGame().getCharacter().getInventory().get("balsa"));
		// Location location = game.getGame().getCharacter().getLocation();
		// Assert.assertEquals("Oeste", location.getName());
	}
	/*
	 * @Test public void testGrab() { game.sendCommand("agarrar brujula");
	 * game.sendCommand("usar brujula"); game.sendCommand("ir oeste");
	 * game.sendCommand("agarrar hacha"); Weapon axe = (Weapon)
	 * game.getGame().getCharacter().getInventory().get("hacha");
	 * Assert.assertNotNull(axe); }
	 * 
	 * @Test public void testGrabBottle() { game.sendCommand("agarrar botella");
	 * game.sendCommand("agarrar agua de catarata con botella"); Bottle botella =
	 * (Bottle) game.getGame().getCharacter().getInventory().get("botella");
	 * Assert.assertNotNull(botella.getContent()); }
	 */
}
