package items;

import island.GameObject;
import tools.Gender;
import tools.ItemType;

public class Item extends GameObject {
	private ItemType type;

	public Item(int id, Gender gender, String name, String description, ItemType type) {
		super(id, gender, name, description);
		this.type = type;
	}

	public Item() {
	}

	public ItemType getType() {
		return type;
	}

}
