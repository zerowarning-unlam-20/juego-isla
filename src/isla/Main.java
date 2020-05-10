package isla;

import java.util.ArrayList;

import entidades.Personaje;
import items.Acceso;
import items.Herramienta;
import items.Item;
import locaciones.Locacion;

public class Main {

	public static void main(String[] args) {
		ArrayList<Item> items = new ArrayList<>();
		ArrayList<Acceso> accesos = new ArrayList<>();
		items.add(new Herramienta("Destornillador", "Destornillador algo oxidado y con el mango gastado"));
		ArrayList<Locacion> locaciones = new ArrayList<>();	
		Locacion cueva = new Locacion("Cueva","Cueva oscura", null, items, accesos);
		locaciones.add(cueva);

		Locacion principal = new Locacion("Costa sur", "Playa, patos", locaciones, null, accesos);

		accesos.add(new Acceso("Puerta","Puerta blanca", principal, cueva, true, false));
		accesos.add(new Acceso("Puerta","Puerta negra", principal, cueva, true, false));

		Personaje personaje = new Personaje(principal);
		personaje.cambiarLocacion(principal);
		System.out.println(personaje);
		
		System.out.println();		
		System.out.println("--------------------");
		System.out.println(personaje.getLocacion());
		System.out.println(personaje.getLocacion().getAccesos());
		
		System.out.println(personaje.getLocacion().estaBloqueada(cueva));
		personaje.cambiarLocacion(personaje.getLocacion().getLocaciones().get(0));
		accesos.get(0).desbloquear();
		personaje.cambiarLocacion(personaje.getLocacion().getLocaciones().get(0));
		
		
	}

}
