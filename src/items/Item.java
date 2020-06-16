package items;

import com.google.gson.annotations.SerializedName;

import island.GameObject;
import tools.Gender;
import tools.ItemType;

public class Item extends GameObject {
	@SerializedName("itemType") // Le digo al gson que lo lea asi en el texto
	private ItemType itemType;

	public Item(int id, Gender gender, String name, String description, ItemType type) {
		super(id, gender, name, description);
		this.itemType = type;
	}

	public ItemType getType() {
		return itemType;
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
		if (itemType != other.itemType)
			return false;
		return true;
	}
}
