package entities;

public class Liquid extends Item {

	public Liquid(int id, String name, String description, boolean visible) {
		super(id, name, description, visible);
	}

	@Override
	public void use(Item objetive) {
		// TODO Auto-generated method stub
		System.out.println("Usar con que");
	}

	@Override
	public void recieveObject(GameObject recieved) {
		// TODO Auto-generated method stub

	}
}
