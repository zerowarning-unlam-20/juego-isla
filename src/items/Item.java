package items;

import island.GameObject;
import items.properties.Inspectable;
import tools.Gender;

public class Item extends GameObject implements Inspectable {

	public Item(Gender gender, String name, String description) {
		super(gender, name, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String inspect() {
		return this.getDescription();
	}
}
