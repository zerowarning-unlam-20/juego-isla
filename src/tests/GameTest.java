package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entities.Access;
import entities.UserCharacter;
import manager.GameManager;
import entities.Location;

class GameTest {

	@Test
	void test() {
		Location s1 = new Location(1, "s1", "Inicio", true, false);
		s1.addAccess(new Access(12, "Puerta", "Puerta azul", true, false, true, 2, 0));

		Location s2 = new Location(2, "s2", "Segundo lugar", true, false);
		s2.addAccess(new Access(12, "Puerta", "Puerta azul", true, false, true, 1, 0));
		s2.addAccess(new Access(23, "Puerta", "Puerta azul", true, false, true, 3, 0));
		s2.addAccess(new Access(25, "Puerta", "Puerta azul", true, false, true, 5, 0));
		s2.addAccess(new Access(26, "Puerta", "Puerta azul", true, false, true, 6, 0));

		Location s3 = new Location(3, "s3", "Tercer lugar", true, false);
		s3.addAccess(new Access(23, "Puerta", "Puerta azul", true, false, true, 2, 0));
		s3.addAccess(new Access(34, "Puerta", "Puerta azul", true, false, true, 4, 0));
		
		Location s4 = new Location(4, "s4", "Cuarto lugar", true, false);
		s4.addAccess(new Access(34, "Puerta", "Puerta azul", true, false, true, 3, 0));
		s4.addAccess(new Access(45, "Puerta", "Puerta azul", true, false, true, 5, 0));

		Location s5 = new Location(5, "s5", "Quinto lugar", true, false);
		s5.addAccess(new Access(45, "Puerta", "Puerta azul", true, false, true, 4, 0));
		s5.addAccess(new Access(25, "Puerta", "Puerta azul", true, false, true, 2, 0));

		Location s6 = new Location(6, "s6", "Sexto lugar", true, false);
		s6.addAccess(new Access(26, "Puerta", "Puerta azul", true, false, true, 2, 0));

		ArrayList<Location> ubicaciones = new ArrayList<>();
		ubicaciones.add(s1);
		ubicaciones.add(s2);
		ubicaciones.add(s3);
		ubicaciones.add(s4);
		ubicaciones.add(s5);
		ubicaciones.add(s5);
		ubicaciones.add(s6);

		UserCharacter p = new UserCharacter(s1);

		GameManager gm = new GameManager(p, ubicaciones);
		
		gm.getCharacter().goTo(s2.getName());
		gm.getCharacter().goTo(s5.getName());
		gm.getCharacter().goTo(s4.getName());

		Assert.assertEquals(s4, gm.getCharacter().getLocation());
	}

}
