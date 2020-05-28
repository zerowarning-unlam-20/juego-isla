package entities;

import island.GameObject;
import items.Item;
import items.Location;
import tools.Gender;

public class UserCharacter extends Entity {

	public UserCharacter(Location location) {
		super(0, Gender.M, "Carlos", "Tachero", location);
	}
	public UserCharacter(String name,Location location) {
		super(0, Gender.M, name, "Tachero", location);
	}
	@Override
	public void drink(Item item) {
		if (item != null)
			state = state.drink(item);
	}

	@Override
	public void open(Item item) {
		if (item != null)
			state.open(item);
	}

	@Override
	public void attack(Item item, GameObject gameObject) {
		gameObject.recieveDamage(0D);
	}

	@Override
	public void look(GameObject gameObject) {
		if (gameObject != null)
			state.look(gameObject);
	}

	@Override
	public void unlock(Item toUnlock) {
		if (toUnlock != null)
			state.unlock(toUnlock);
	}

	@Override
	public String toString() {
		return super.toString() + "\n Locacion=" + location + "\n Inventario=" + getInventory();
	}

	public void addItem(Item item) {
		if (item != null)
			inventory.add(item);
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

	@Override
	public boolean recieveObject(GameObject object) {
		if (object.getClass() == Item.class) {
			inventory.add((Item) object);
			return true;
		}
		return false;
	}

	@Override
	public void recieveDamage(Double damage) {
	}
}
