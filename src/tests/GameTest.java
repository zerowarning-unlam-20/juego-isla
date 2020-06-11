package tests;

class GameTest {
	/*
	 * @Test public void testAccederMadera() { Location s1 = new Location(1,
	 * Gender.M, "s1", "Inicio", true); s1.addAccess(new Access(2, Gender.F,
	 * "Puerta", "Puerta azul", false, true, 2, 0)); Tool tool = new Tool(3,
	 * Gender.M, "Hacha", "Hacha de hierro", ItemType.WOOD); UserCharacter p = new
	 * UserCharacter(s1); Container palmTree = new Container(4, Gender.F, "Palmera",
	 * "Palmera grande", ItemType.WOOD, true); Item wood = new Item(5, Gender.F,
	 * "Madera", "Madera de palmera", ItemType.UNBREAKABLE);
	 * palmTree.addContent(wood); p.addItem(tool);
	 * 
	 * ArrayList<Location> locations = new ArrayList<Location>(); locations.add(s1);
	 * GameManager gameManager = new GameManager(); gameManager.setGame(new Game(p,
	 * locations));
	 * 
	 * p.use(tool, palmTree); Assert.assertTrue(!palmTree.isLocked());
	 * p.grab(palmTree.getFirst()); Assert.assertEquals(wood,
	 * p.getInventory().get(1)); }
	 */
	/*
	 * @Test public void cargarTodasUbicacionesJuego() { // Esta incompleta
	 * System.out.println("----------------TEST JUEGO PROPIO------------------");
	 * IdManager.setLastId(0); System.out.println(
	 * "Te despertas en una isla. Estás solo. Lo que querés es volver a tu hogar. Contigo tienes una brújula y un diario"
	 * ); Location norte = new Location(IdManager.getNext(), Gender.M, "s1",
	 * "Estas en el Norte! Observas un mar calmo y muchas piedras puntiagudas que te prohiben el paso. También cerca tuyo hay una cascada que proviene del centro de la isla.\r\n"
	 * + "", true); Location sur = new Location(IdManager.getNext(), Gender.M, "s2",
	 * "Estas en el Sur! Observas más mar, observas muchas palmeras y lianas.",
	 * true); Location este = new Location(IdManager.getNext(), Gender.M, "s3",
	 * "Estas en el Este! Hay un hacha en la costa y el mar se observa muy calmo.",
	 * true); Location oeste = new Location(IdManager.getNext(), Gender.M, "s4",
	 * "Estas en el Oeste! Hay una cueva y observas una botella vacía, mucha arena y más mar. Observas muchos tiburones a lo lejos que impedirían tu paso."
	 * , true); Location cueva = new Location(IdManager.getNext(), Gender.M, "s5",
	 * "Entras en la cueva, está bastante oscura, sin embargo, observas algo que parece ser, ¡¡UN CADAVER!! "
	 * , true); Location mar = new Location(IdManager.getNext(), Gender.M, "s6", "",
	 * true); System.out.println("DESCRIPCION NORTE: " + norte.getDescription());
	 * Tool brujula = new Tool(IdManager.getNext(), Gender.F, "brujula",
	 * "Esto es una brujula. Gracias a ella sabras donde estan los puntos cardinales"
	 * , ItemType.UNBREAKABLE); // Ahora cargo los accesos. Desde el oeste se puede
	 * ir a la cueva. Desde el este // puedo ir al Mar (spoiler) (
	 * norte.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino",
	 * "Sendero que te lleva hacia el sur ", true, true, sur.getId(),
	 * brujula.getId())); norte.addAccess(new Access(IdManager.getNext(), Gender.F,
	 * "Camino", "Sendero que te lleva hacia el este ", true, true, este.getId(),
	 * brujula.getId())); norte.addAccess(new Access(IdManager.getNext(), Gender.F,
	 * "Camino", "Sendero que te lleva hacia el oeste", true, true, oeste.getId(),
	 * brujula.getId())); sur.addAccess(new Access(IdManager.getNext(), Gender.F,
	 * "Camino", "Sendero que te lleva hacia el oeste", false, true, norte.getId(),
	 * 0)); sur.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino",
	 * "Sendero que te lleva hacia el oeste", false, true, este.getId(), 0));
	 * sur.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino",
	 * "Sendero que te lleva hacia el oeste", false, true, oeste.getId(), 0));
	 * este.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino",
	 * "Sendero que te lleva hacia el oeste", false, true, norte.getId(), 0));
	 * este.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino",
	 * "Sendero que te lleva hacia el oeste", false, true, sur.getId(), 0));
	 * este.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino",
	 * "Sendero que te lleva hacia el oeste", false, true, oeste.getId(), 0)); //
	 * este.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino", "Sendero
	 * // que te lleva hacia el oeste", false, true, oeste.getId(), balsa.getId()));
	 * // todavia no tengo la balsa creada para crear este acceso.
	 * oeste.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino",
	 * "Sendero que te lleva hacia el oeste", false, true, norte.getId(), 0));
	 * oeste.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino",
	 * "Sendero que te lleva hacia el oeste", false, true, sur.getId(), 0));
	 * oeste.addAccess(new Access(IdManager.getNext(), Gender.F, "Camino",
	 * "Sendero que te lleva hacia el oeste", false, true, este.getId(), 0));
	 * oeste.addAccess(new Access(IdManager.getNext(), Gender.F, "Entrada pequeña",
	 * "Entrada pequeña para entrar a una cueva", false, true, cueva.getId(), 0));
	 * // Agregarle objetos a cada ubicacion y probar si se pueden agregar y usar }
	 */

}
