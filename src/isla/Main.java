package isla;

import java.util.ArrayList;

import ObjetosJuego.Acceso;
import ObjetosJuego.Ubicacion;
import entidades.Personaje;

public class Main {

	public static void main(String[] args) {
		Ubicacion final1 = new Ubicacion(3, "Costa", "", true, null, null, null, false);
		Ubicacion final2 = new Ubicacion(3, "Montaña", "", true, null, null, null, false);
		ArrayList<Ubicacion> ubicaciones = new ArrayList<>();
		ubicaciones.add(final1);
		ubicaciones.add(final2);

		Acceso accesoF1 = new Acceso(5, "Puerta de madera", "Barnizada", true, false, true, final1, 0);
		Acceso accesoF2 = new Acceso(6, "Puerta de madera", "Barnizada", true, false, true, final2, 0);
		ArrayList<Acceso> accesos = new ArrayList<>();
		accesos.add(accesoF1);
		accesos.add(accesoF2);

		Ubicacion inicio = new Ubicacion(1, "Casa", "", true, ubicaciones, null, accesos, false);

		/////
		ArrayList<Ubicacion> ubicacionesF1 = new ArrayList<>();
		ubicacionesF1.add(inicio);
		ArrayList<Acceso> accesosF1 = new ArrayList<>();
		accesosF1.add(new Acceso(5, "Puerta de madera", "Barnizada", true, false, true, inicio, 0));
		final1.setUbicaciones(ubicacionesF1);
		final1.setAccesos(accesosF1);
		/////

		/////
		ArrayList<Ubicacion> ubicacionesF2 = new ArrayList<>();
		ubicacionesF2.add(inicio);
		ArrayList<Acceso> accesosF2 = new ArrayList<>();
		accesosF2.add(new Acceso(6, "Puerta de madera", "Barnizada", true, false, true, inicio, 0));
		final2.setUbicaciones(ubicacionesF2);
		final2.setAccesos(accesosF2);
		/////

		Personaje p = new Personaje(inicio);
		System.out.println(p);

		p.cambiarUbicacion(final1);
		System.out.println(p);

		p.cambiarUbicacion(inicio);
		System.out.println(p);

		p.cambiarUbicacion(final2);
		System.out.println(p);

		System.out.println(p.verAlrededor());
		System.out.println(p.verInventario());
	}

}
