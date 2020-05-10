package items;

import estados.Estado;
import estados.Normal;
import interfaces.Tomable;

public class Liquido extends Item implements Tomable {

	public Liquido(String nombre, String descripcion) {
		super(nombre, descripcion);
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
