package entities;

public class Tool extends Item {

	public Tool(int id, String nombre, String descripcion, boolean visible) {
		super(id, nombre, descripcion, visible);
	}

	public Tool() {

	}

	@Override
	public void use(Item objetivo) {
		objetivo.recieveObject(this);
	}

	@Override
	public void recieveObject(GameObject objeto) {
		// TODO Auto-generated method stub

	}

}
