package entities;

import java.util.List;

import island.Location;
import items.Item;
import manager.GameManager;
import tools.Gender;

public class UserCharacter extends Entity {

	public UserCharacter(GameManager gameManager, Location location) {
		super(gameManager, 0, Gender.M, "Test", "Test Character", location);
		initialLocation = location.getId();
	}

	public UserCharacter(GameManager gameManager, Location location, List<Item> inventory) {
		super(gameManager, 0, Gender.M, "Test", "Test Character", location);
		initialLocation = location.getId();
		this.inventory = inventory;
	}

	public UserCharacter(GameManager gameManager, int id, Gender gender, String name, String description,
			List<Item> inventory, int initialLocation) {
		super(gameManager, id, gender, name, description, inventory, initialLocation);
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
}
