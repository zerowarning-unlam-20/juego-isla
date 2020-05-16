package island;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entities.Access;
import entities.UserCharacter;
import entities.Location;

class GameTest {

	@Test
	void test() {
		Location s1 = new Location(1, "s1", "Inicio", true, false);
		s1.addAccess(new Access(7, "Puerta", "Puerta azul", true, false, true, 2, 0));
		
		Location s2 = new Location(2, "s2", "Segundo lugar", true, false);
		s2.addAccess(new Access(8, "Puerta", "Puerta azul", true, false, true, 1, 0));
		s2.addAccess(new Access(9, "Puerta", "Puerta azul", true, false, true, 3, 0));
		s2.addAccess(new Access(16, "Puerta", "Puerta azul", true, false, true, 5, 0));
		s2.addAccess(new Access(17, "Puerta", "Puerta azul", true, false, true, 6, 0));
		
		Location s3 = new Location(3, "s3", "Tercer lugar", true, false);
		s3.addAccess(new Access(10, "Puerta", "Puerta azul", true, false, true, 2, 0));
		s3.addAccess(new Access(11, "Puerta", "Puerta azul", true, false, true, 4, 0));
		s3.addAccess(new Access(7, "Puerta", "Puerta azul", true, false, true, 2, 0));
		
		Location s4 = new Location(4, "s4", "Cuarto lugar", true, false);
		s4.addAccess(new Access(12, "Puerta", "Puerta azul", true, false, true, 3, 0));
		s4.addAccess(new Access(13, "Puerta", "Puerta azul", true, false, true, 5, 0));
		
		Location s5 = new Location(5, "s5", "Quinto lugar", true, false);
		s5.addAccess(new Access(14, "Puerta", "Puerta azul", true, false, true, 4, 0));
		s5.addAccess(new Access(15, "Puerta", "Puerta azul", true, false, true, 2, 0));
		
		Location s6 = new Location(6, "s6", "Sexto lugar", true, false);
		s6.addAccess(new Access(18, "Puerta", "Puerta azul", true, false, true, 6, 0));
		
		ArrayList<Location> ubicaciones = new ArrayList<>();
		ubicaciones.add(s1);
		ubicaciones.add(s2);
		ubicaciones.add(s3);
		ubicaciones.add(s4);
		ubicaciones.add(s5);
		ubicaciones.add(s5);
		ubicaciones.add(s6);
		
		for(Location ubicacion : ubicaciones) {
			for(Location otro : ubicaciones) {
				if(!ubicacion.equals(otro)) {
					ubicacion.addLink(otro);
				}
			}
		}
		
		UserCharacter p = new UserCharacter(s1);
		p.changeLocation(s2);
		p.changeLocation(s5);
		p.changeLocation(s4);
		
		Assert.assertEquals(s4,p.getLocation());
	}

}
