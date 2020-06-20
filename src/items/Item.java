package items;

import com.google.gson.annotations.SerializedName;

import island.GameObject;
import items.properties.Inspectable;
import tools.Gender;

public class Item extends GameObject implements Inspectable {

	public Item(int id, Gender gender, String name, String description) {
		super(id, gender, name, description);
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
		return true;
	}

	@Override
	public String inspect() {
		return this.getDescription();
	}
}
