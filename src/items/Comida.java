package items;

import interfaces.Comible;

public class Comida extends Item implements Comible {

	public Comida(String nombre, String descripcion) {
		super(nombre, descripcion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void comer() {
		// TODO Auto-generated method stub
		System.out.println("Omnomnom");
	}

	@Override
	public void usar(Item objetivo) {
		// TODO Auto-generated method stub
		System.out.println("Usar con" + objetivo);
	}

}
