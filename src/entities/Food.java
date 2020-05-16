package entities;

import interfaces.Edible;

public class Food extends Item implements Edible {

	public Food(int id, String nombre, String descripcion, boolean visible) {
		super(id, nombre, descripcion, visible);
	}

	public Food() {
	}
	
	@Override
	public void comer() {
		// TODO Auto-generated method stub
		System.out.println("Omnomnom");
	}

	@Override
	public void use(Item objetivo) {
		// TODO Auto-generated method stub
		System.out.println("Usar con" + objetivo);
	}

	@Override
	public void recieveObject(GameObject objeto) {
		// TODO Auto-generated method stub
		
	}

}
