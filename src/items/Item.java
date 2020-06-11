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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (type != other.type)
			return false;
		return true;
	}
}
