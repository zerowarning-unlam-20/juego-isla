package items.types;

import items.Item;
import items.properties.Installable;
import tools.Gender;

public class Key extends Item implements Installable {

	public Key(Gender gender, String name, String description, int price) {
		super(gender, name, description, price);
	}

}
