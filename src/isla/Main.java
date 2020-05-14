package isla;

import java.util.ArrayList;

import ObjetosJuego.Acceso;
import ObjetosJuego.Ubicacion;
import entidades.Personaje;

public class Main {

	public static void main(String[] args) {
		Ubicacion s1 = new Ubicacion(1, "s1", "Inicio", true, false);
		s1.agregarAcceso(new Acceso(7, "Puerta", "Puerta azul", true, false, true, 2, 0));
		
		Ubicacion s2 = new Ubicacion(2, "s2", "Segundo lugar", true, false);
		s2.agregarAcceso(new Acceso(8, "Puerta", "Puerta azul", true, false, true, 1, 0));
		s2.agregarAcceso(new Acceso(9, "Puerta", "Puerta azul", true, false, true, 3, 0));
		s2.agregarAcceso(new Acceso(16, "Puerta", "Puerta azul", true, false, true, 5, 0));
		s2.agregarAcceso(new Acceso(17, "Puerta", "Puerta azul", true, false, true, 6, 0));
		
		Ubicacion s3 = new Ubicacion(3, "s3", "Tercer lugar", true, false);
		s3.agregarAcceso(new Acceso(10, "Puerta", "Puerta azul", true, false, true, 2, 0));
		s3.agregarAcceso(new Acceso(11, "Puerta", "Puerta azul", true, false, true, 4, 0));
		s3.agregarAcceso(new Acceso(7, "Puerta", "Puerta azul", true, false, true, 2, 0));
		
		Ubicacion s4 = new Ubicacion(4, "s4", "Cuarto lugar", true, false);
		s4.agregarAcceso(new Acceso(12, "Puerta", "Puerta azul", true, false, true, 3, 0));
		s4.agregarAcceso(new Acceso(13, "Puerta", "Puerta azul", true, false, true, 5, 0));
		
		Ubicacion s5 = new Ubicacion(5, "s5", "Quinto lugar", true, false);
		s5.agregarAcceso(new Acceso(14, "Puerta", "Puerta azul", true, false, true, 4, 0));
		s5.agregarAcceso(new Acceso(15, "Puerta", "Puerta azul", true, false, true, 2, 0));
		
		Ubicacion s6 = new Ubicacion(6, "s6", "Sexto lugar", true, false);
		s6.agregarAcceso(new Acceso(18, "Puerta", "Puerta azul", true, false, true, 6, 0));
		
		ArrayList<Ubicacion> ubicaciones = new ArrayList<>();
		ubicaciones.add(s1);
		ubicaciones.add(s2);
		ubicaciones.add(s3);
		ubicaciones.add(s4);
		ubicaciones.add(s5);
		ubicaciones.add(s5);
		ubicaciones.add(s6);
		
		for(Ubicacion ubicacion : ubicaciones) {
			for(Ubicacion otro : ubicaciones) {
				if(!ubicacion.equals(otro)) {
					ubicacion.agregarVinculo(otro);
				}
			}
		}
		
		Personaje p = new Personaje(s1);
		System.out.println(p.verAlrededor());
		p.cambiarUbicacion(s2);
		System.out.println(p.verAlrededor());
		p.cambiarUbicacion(s5);
		p.cambiarUbicacion(s4);
		System.out.println(p.verAlrededor());
	}

}
