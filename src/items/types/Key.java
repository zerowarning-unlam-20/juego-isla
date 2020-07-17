package items.types;

import items.Item;
import items.properties.Installable;
import tools.Gender;
import tools.Namber;

public class Key extends Item implements Installable {

	public Key(Gender gender, Namber number, String name, String description, int price) {
		super(gender, number, name, description, price);
	}

}
