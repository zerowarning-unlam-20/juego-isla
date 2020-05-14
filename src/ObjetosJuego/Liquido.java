package ObjetosJuego;

import estados.Estado;
import estados.Normal;
import interfaces.Tomable;

public class Liquido extends Item implements Tomable {

	public Liquido(int id, String nombre, String descripcion, boolean visible) {
		super(id, nombre, descripcion, visible);
	}

	@Override
	public void usar(Item objetivo) {
		// TODO Auto-generated method stub
		System.out.println("Usar con que");
	}

	@Override
	public Estado tomar() {
		// TODO Auto-generated method stub
		return new Normal();
	}
}
