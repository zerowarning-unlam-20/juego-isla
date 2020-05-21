package items;

import island.GameObject;
import tools.Gender;
import tools.ItemType;

public class Liquid extends Item {

	public Liquid(int id, Gender gender, String name, String description, boolean visible) {
		super(id, gender, name, description, ItemType.UNBREAKABLE);
	}

	@Override
	public void use(Item objetive) {
		// TODO Auto-generated method stub
		System.out.println("Usar con que");
	}

	@Override
	public boolean recieveObject(GameObject recieved) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void recieveDamage(Double damage) {
		// TODO Auto-generated method stub
		
	}
}
