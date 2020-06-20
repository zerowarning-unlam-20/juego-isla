package items.types;

import items.Item;
import items.properties.Installable;
import tools.Gender;

public class Key extends Item implements Installable {

	public Key(int id, Gender gender, String name, String description) {
		super(id, gender, name, description);
	}

}
