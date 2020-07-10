package items.types;

import java.util.List;

import items.Item;
import items.properties.Unlockable;
import tools.Gender;

public class KeyBox extends Box implements Unlockable {
	private String idKey;

	public KeyBox(Gender gender, String name, String description, boolean locked, String idKey, List<Item> items) {
		super(gender, name, description, locked, idKey, items);
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
