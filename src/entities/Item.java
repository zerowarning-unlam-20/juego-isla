package entities;

public abstract class Item extends GameObject {
	
	public Item(int id, String name, String description, boolean visible) {
		super(id, name, description, visible);
	}
	
	public Item() {
	}
	
	public abstract void use(Item objetive);

}
