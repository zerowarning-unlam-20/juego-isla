package entities;

public class Container extends Item {
	private Item content;

	public Container(int id, String name, String description, boolean visible) {
		super(id, name, description, visible);
	}

	public Container(int id, String name, String description, boolean visible, Item content) {
		super(id, name, description, visible);
		this.content = content;
		description = name + " con " + content.name;
	}

	public Item getContent() {
		return content;
	}

	public void setContent(Item content) {
		this.content = content;
		description = name + " con " + content.name;
	}

	@Override
	public String toString() {
		String result;
		if (content != null)
			result = super.toString() + "\n tiene: " + content;
		else
			result = super.toString() + "\n vacio";
		return result;

	}

	@Override
	public void use(Item objective) {
		objective.recieveObject(content);
	}

	@Override
	public void recieveObject(GameObject object) {
		content = (Item) object;
		description = name + " con " + object.name;
	}

	public void empty() {
		content = null;
	}
}
