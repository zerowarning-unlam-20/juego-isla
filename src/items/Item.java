package items;

import island.GameObject;
import tools.Gender;
import tools.ItemType;

public class Item extends GameObject {
	protected ItemType type;

	public Item(int id, Gender gender, String name, String description, ItemType type) {
		super(id, gender, name, description);
		this.type = type;
	}

	public Item() {
	}

	public void use(Item objective) {
		objective.recieveObject(this);
	}

	public ItemType getType() {
		return type;
	}

	@Override
	public boolean recieveObject(GameObject object) {
		return false;
	}

	@Override
	public void recieveDamage(Double damage) {
	}

}
