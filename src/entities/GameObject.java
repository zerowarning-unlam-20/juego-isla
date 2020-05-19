package entities;

public abstract class GameObject {
	protected int id;
	protected String name;
	protected String description;
	protected boolean visible;

	public GameObject(int id, String name, String description, boolean visible) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.visible = visible;
	}

	public GameObject() {
		name = "";
		description = "";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public abstract void recieveObject(GameObject object);
	
	@Override
	public String toString() {
		return description;
	}
}
