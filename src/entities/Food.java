package entities;

import interfaces.Edible;

public class Food extends Item implements Edible {

	public Food(int id, String name, String description, boolean visible) {
		super(id, name, description, visible);
	}

	public Food() {
	}
	
	@Override
	public void comer() {
		// TODO Auto-generated method stub
		System.out.println("Omnomnom");
	}

	@Override
	public void use(Item objective) {
		// TODO Auto-generated method stub
		System.out.println("Usar con" + objective);
	}

	@Override
	public void recieveObject(GameObject object) {
		// TODO Auto-generated method stub
		
	}

}
