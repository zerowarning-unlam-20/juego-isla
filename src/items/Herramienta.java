package items;

import interfaces.Remable;
import interfaces.Tirable;

public class Herramienta extends Item implements Tirable, Remable {

	public Herramienta(String nombre, String descripcion) {
		super(nombre, descripcion);
	}

	@Override
	public void usar(Item objetivo) {
		// TODO Auto-generated method stub
		System.out.println("Usar con que");
	}

	@Override
	public void remar() {
		// TODO Auto-generated method stub
		System.out.println("Remar con que");
	}

	@Override
	public void tirar() {
		// TODO Auto-generated method stub
		System.out.println("Para maestro");
	}

}
