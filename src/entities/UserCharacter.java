package entities;

import java.util.List;

import island.Location;
import items.Access;
import items.Item;
import manager.GameManager;
import tools.Gender;

public class UserCharacter extends Entity {

	public UserCharacter(GameManager gameManager, Location location) {
		super(gameManager, 0, Gender.M, "Test", "Test Character", location);
	}

	public UserCharacter(GameManager gameManager, int id, Gender gender, String name, String description,
			Location location, List<Item> inventory) {
		super(gameManager, id, gender, name, description, location, inventory);
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

	public boolean use(Item item) {
		if (item != null) {
			return state.use(item);
		}
		return false;
	}
}
