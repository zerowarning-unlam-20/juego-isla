package entities;

import island.GameObject;
import items.Access;
import items.Item;
import items.Location;
import items.Weapon;
import tools.Gender;

public class UserCharacter extends Entity {

	public UserCharacter(Location location) {
		super(0, Gender.M, "Test", "Test Character", location);
	}

	public UserCharacter(String name, Location location) {
		super(0, Gender.M, name, "Character", location);
	}

	public Item removeItem(Item item) {
		if (item != null)
			inventory.remove(item);
		return item;
	}

	public Item removeItem(int idItem) {
		Item removed = null;
		for (Item i : inventory) {
			if (i.getId() == idItem) {
				removed = i;
				inventory.remove(i);
				break;
			}
		}
		return removed;
	}

	public boolean goTo(int id) {
		boolean result = false;
		Access access = location.getAccesses().get(id);
		if (access != null && access.getDestination() != null) {
			state.goTo(access.getDestination());
			result = true;
		}
		return result;
	}
}
