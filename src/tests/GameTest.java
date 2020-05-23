package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entities.UserCharacter;
import items.Access;
import items.Container;
import items.Item;
import items.Liquid;
import items.Location;
import items.SingleContainer;
import items.Text;
import items.Tool;
import manager.Game;
import manager.GameManager;
import tools.Gender;
import tools.ItemType;

class GameTest {

	@Test
	void testCambiarUbicacion() {
		System.out.println("---------Test cambiar ubicacion---------");
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

		ArrayList<Location> ubicaciones = new ArrayList<>();
		ubicaciones.add(s1);
		ubicaciones.add(s2);
		ubicaciones.add(s3);
		ubicaciones.add(s4);
		ubicaciones.add(s5);
		ubicaciones.add(s5);
		ubicaciones.add(s6);

		UserCharacter p = new UserCharacter(s1);

		Game game = new Game(p, ubicaciones);

		GameManager gm = new GameManager();
		gm.setGame(game);

		game.getCharacter().goTo(s2);
		game.getCharacter().goTo(s5);
		game.getCharacter().goTo(s4);

		Assert.assertEquals(s4, game.getCharacter().getLocation());

		// probar que no pueda pasar directamente a la ubicacion inicial sin pasar por
		// la intermedia
		game.getCharacter().goTo(s2);
		Assert.assertNotEquals(s2, game.getCharacter().getLocation());

		game.getCharacter().goTo(s5);
		Assert.assertEquals(s5, game.getCharacter().getLocation());

		game.getCharacter().goTo(s2);
		Assert.assertEquals(s2, game.getCharacter().getLocation());
	}

	@Test
	public void testGeneroItems() {
		System.out.println("---------Test texto salida con genero---------");
		Location testLocation = new Location(1, Gender.M, "s1", "Inicio", true);
		UserCharacter p = new UserCharacter(testLocation);
		System.out.println(p.getSingularName());
		System.out.println(p.getOnlyName());
	}

	@Test
	public void testAccederMadera() {
		System.out.println("---------Test obtener con herramienta---------");
		Location s1 = new Location(1, Gender.M, "s1", "Inicio", true);
		s1.addAccess(new Access(2, Gender.F, "Puerta", "Puerta azul", false, true, 2, 0));
		Tool tool = new Tool(3, Gender.M, "Hacha", "Hacha de hierro", ItemType.WOOD);
		UserCharacter p = new UserCharacter(s1);
		Container palmTree = new Container(4, Gender.F, "Palmera", "Palmera grande", ItemType.WOOD, true);
		Item wood = new Item(5, Gender.F, "Madera", "Madera de palmera", ItemType.UNBREAKABLE);
		palmTree.addContent(wood);
		p.addItem(tool);

		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add(s1);
		GameManager gameManager = new GameManager();
		gameManager.setGame(new Game(p, locations));

		p.use(tool, palmTree);
		Assert.assertTrue(!palmTree.isLocked());
		p.grab(palmTree.getFirst());
		Assert.assertEquals(wood, p.getInventory().get(1));
	}

	@Test
	public void testBotella() {
		System.out.println("---------Test botella contenido---------");
		Location s1 = new Location(1, Gender.M, "s1", "Inicio", true);
		SingleContainer botella = new SingleContainer(2, Gender.F, "Botella", "Botella de vidrio",
				ItemType.UNBREAKABLE);
		Liquid agua = new Liquid(4, Gender.F, "agua", "Agua de catarata", true);
		SingleContainer catarata = new SingleContainer(3, Gender.F, "Cataratas", "Cataratas con mucha agua", agua, true,
				ItemType.UNBREAKABLE, true);
		UserCharacter p = new UserCharacter(s1);
		p.grab(botella);
		p.grab(catarata.getContent());
		Assert.assertEquals(((SingleContainer) p.getInventory().get(0)).getContent(), agua);
	}

	@Test
	public void testLibro() {
		System.out.println("---------Test libro oscuridad---------");
		GameManager gameManager = new GameManager();
		Location cueva = new Location(1, Gender.M, "s1", "Inicio", false);
		Location s1 = new Location(2, Gender.M, "s1", "Inicio", true);
		Access salida = new Access(3, Gender.F, "Salida", "salida de cueva", false, true, 2, 0);
		cueva.addAccess(salida);
		Text hoja = new Text(4, Gender.F, "Hoja", "Hoja del viajero", true, ItemType.UNBREAKABLE,
				"Se necesitan troncos y " + "lianas para poder armar la canoa. "
						+ "Tambien llevar provisiones (IMPORTANTE COMIDA) y un remo");
		UserCharacter p = new UserCharacter(cueva);
		ArrayList<Location> lugares = new ArrayList<>();
		lugares.add(cueva);
		lugares.add(s1);
		Game game = new Game(p, lugares);
		gameManager.setGame(game);
		p.look(hoja);
		p.goTo(s1);
		p.look(hoja);
	}

}
