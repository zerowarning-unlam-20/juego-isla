package items;

import island.GameObject;
import items.properties.Inspectable;
import tools.Gender;
import tools.Namber;

public class Item extends GameObject implements Inspectable {
	private int value;

	public Item(Gender gender, Namber namber, String name, String description, int value) {
		super(gender, namber, name, description);
		this.value = value;
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

	public int getValue() {
		return value;
	}
}
