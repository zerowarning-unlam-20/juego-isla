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
import tools.IdManager;
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
	}

	public void testUbicacionInvalida() {
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
		Assert.assertFalse(p.look(hoja));
		p.goTo(s1);
		Assert.assertTrue(p.look(hoja));
	}

	@Test
	public void testIdManager() {
		System.out.println("----------------TEST ID MANAGER------------------");
		IdManager.setLastId(0);
		Location s1 = new Location(IdManager.getNext(), Gender.M, "s1", "Inicio", true);
		Location s2 = new Location(IdManager.getNext(), Gender.M, "s2", "Segundo lugar", true);
		Location s3 = new Location(IdManager.getNext(), Gender.M, "s3", "Tercer lugar", true);
		s1.addAccess(new Access(IdManager.getNext(), Gender.F, "Puerta", "Puerta azul hacia s2 ", false, true,
				s2.getId(), 0));
		s2.addAccess(new Access(IdManager.getNext(), Gender.F, "Puerta", "Puerta azul hacia s1 ", false, true,
				s1.getId(), 0));
		s2.addAccess(new Access(IdManager.getNext(), Gender.F, "Puerta", "Puerta roja hacia s3", false, true,
				s3.getId(), 0));

		s3.addAccess(new Access(IdManager.getNext(), Gender.F, "Puerta", "Puerta verde hacia s1", false, true,
				s1.getId(), 0));
		s3.addAccess(new Access(IdManager.getNext(), Gender.F, "Puerta", "Puerta roja hacia s2", false, true,
				s2.getId(), 0));
		System.out.println("Accesos s1: " + s1.getAccesses());
		System.out.println("Accesos s2: " + s2.getAccesses());
		System.out.println("Accesos s3: " + s3.getAccesses());
		System.out.println();
		Assert.assertEquals(9, IdManager.getNext()); // Antes de esta linea tenia 8 el contador del IdManager
	}

	@Test
	public void testUnlockAcceses() {
		System.out.println("-------------TEST BRUJULA ABRE SUR----------------------");
		IdManager.setLastId(0);
		Tool brujula = new Tool(IdManager.getNext(), Gender.F, "brujula",
				"Esto es una brujula. Gracias a ella sabras donde estan los puntos cardinales", ItemType.UNBREAKABLE);
		Location norte = new Location(IdManager.getNext(), Gender.M, "Norte", "Norte. Aca inicia la aventura", true);
		Location sur = new Location(IdManager.getNext(), Gender.M, "Sur", "Sur. Linda vista a la antartida", true);
		Access accesoNorteASur = (new Access(IdManager.getNext(), Gender.F, "Puerta", "Puerta azul hacia s2 ", true,
				true, sur.getId(), brujula.getId()));
		norte.addAccess(accesoNorteASur);
		sur.addAccess(new Access(IdManager.getNext(), Gender.F, "Puerta", "Puerta azul hacia s1 ", false, true,
				norte.getId(), 0));
		UserCharacter p = new UserCharacter("Juan Benitez", norte);
		p.addItem(brujula);
		ArrayList<Location> lugares = new ArrayList<>();
		lugares.add(norte);
		lugares.add(sur);
		Game game = new Game(p, lugares);
		GameManager gameManager = new GameManager();
		gameManager.setGame(game);
		if (!accesoNorteASur.isLocked()) { // No deberia entrar al If
			System.out.println("Podes acceder al sur!");
			p.goTo(sur); //
		} else {
			System.out.println("No podes acceder al sur. Esta lockeado");
		}
		System.out.println("Estoy parado en: " + p.getLocation()); // Deberia seguir en el norte
		accesoNorteASur.recieveObject(brujula);
		if (!accesoNorteASur.isLocked()) { // Deberia entrar al If
			System.out.println("Podes acceder al sur!");
			p.goTo(sur); //
		} else {
			System.out.println("No podes acceder al sur. Esta lockeado");
		}
		System.out.println("Estoy parado en: " + p.getLocation()); // Deberia estar en el sur
		System.out.println();
		Assert.assertEquals(sur, game.getCharacter().getLocation());
	}

	@Test
	public void cargarTodasUbicacionesJuego() { // Esta incompleta
		System.out.println("----------------TEST JUEGO PROPIO------------------");
		IdManager.setLastId(0);
		System.out.println(
				"Te despertas en una isla. Estás solo. Lo que querés es volver a tu hogar. Contigo tienes una brújula y un diario");
		Location norte = new Location(IdManager.getNext(), Gender.M, "s1",
				"Estas en el Norte! Observas un mar calmo y muchas piedras puntiagudas que te prohiben el paso. También cerca tuyo hay una cascada que proviene del centro de la isla.\r\n"
						+ "",
				true);
		Location sur = new Location(IdManager.getNext(), Gender.M, "s2",
				"Estas en el Sur! Observas más mar, observas muchas palmeras y lianas.", true);
		Location este = new Location(IdManager.getNext(), Gender.M, "s3",
				"Estas en el Este! Hay un hacha en la costa y el mar se observa muy calmo.", true);
		Location oeste = new Location(IdManager.getNext(), Gender.M, "s4",
				"Estas en el Oeste! Hay una cueva y observas una botella vacía, mucha arena y más mar. Observas muchos tiburones a lo lejos que impedirían tu paso.",
				true);
		Location cueva = new Location(IdManager.getNext(), Gender.M, "s5",
				"Entras en la cueva, está bastante oscura, sin embargo, observas algo que parece ser, ¡¡UN CADAVER!! ",
				true);
		Location mar = new Location(IdManager.getNext(), Gender.M, "s6", "", true);
		System.out.println("DESCRIPCION NORTE: " + norte.getDescription());
		Tool brujula = new Tool(IdManager.getNext(), Gender.F, "brujula",
				"Esto es una brujula. Gracias a ella sabras donde estan los puntos cardinales", ItemType.UNBREAKABLE);
		// Ahora cargo los accesos. Desde el oeste se puede ir a la cueva. Desde el este
		// puedo ir al Mar (spoiler) (
		norte.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el sur ", true,
				true, sur.getId(), brujula.getId()));
		norte.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el este ", true,
				true, este.getId(), brujula.getId()));
		norte.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el oeste", true,
				true, oeste.getId(), brujula.getId()));
		sur.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el oeste", false,
				true, norte.getId(), 0));
		sur.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el oeste", false,
				true, este.getId(), 0));
		sur.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el oeste", false,
				true, oeste.getId(), 0));
		este.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el oeste", false,
				true, norte.getId(), 0));
		este.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el oeste", false,
				true, sur.getId(), 0));
		este.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el oeste", false,
				true, oeste.getId(), 0));
		// este.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero
		// que te lleva hacia el oeste", false, true, oeste.getId(), balsa.getId()));
		// todavia no tengo la balsa creada para crear este acceso.
		oeste.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el oeste",
				false, true, norte.getId(), 0));
		oeste.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el oeste",
				false, true, sur.getId(), 0));
		oeste.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero que te lleva hacia el oeste",
				false, true, este.getId(), 0));
		oeste.addAccess(new Access(IdManager.getNext(), Gender.F, "Entrada pequeña",
				"Entrada pequeña para entrar a una cueva", false, true, cueva.getId(), 0));
		// Agregarle objetos a cada ubicacion y probar si se pueden agregar y usar
	}

}
