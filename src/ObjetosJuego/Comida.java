package ObjetosJuego;

import interfaces.Comible;

public class Comida extends Item implements Comible {

	public Comida(int id, String nombre, String descripcion, boolean visible) {
		super(id, nombre, descripcion, visible);
	}

	public Comida(int id, String nombre, String descripcion) {
		super(id, nombre, descripcion);
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
