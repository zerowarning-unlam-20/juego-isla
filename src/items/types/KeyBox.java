package items.types;

import java.util.List;

import items.Item;
import items.properties.Unlockable;
import tools.Gender;
import tools.Namber;

public class KeyBox extends Box implements Unlockable {
	private String idKey;

	public KeyBox(Gender gender, Namber number, String name, String description, int price, boolean locked,
			String idKey, List<Item> items) {
		super(gender, number, name, description, price, locked, idKey, items);
	}

	@Override
	public void unlock() {
		locked = false;
	}

	@Override
	public boolean unlock(Item keyItem) {
		boolean result = false;
		if (locked) {
			if (keyItem.getName().equalsIgnoreCase(idKey)) {
				locked = false;
			}
			result = true;
		}
		return result;
	}
}
