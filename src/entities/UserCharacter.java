package entities;

import island.GameObject;
import items.Access;
import items.Item;
import items.Location;
import tools.Gender;

public class UserCharacter extends Entity {

	public UserCharacter(Location location) {
		super(0, Gender.M, "Test", "Test Character", location);
	}

	public UserCharacter(String name, Location location) {
		super(0, Gender.M, name, "Character", location);
	}

	@Override
	public boolean drink(Item item) {
		if (item != null)
			state = state.drink(item);
		return item != null;
	}

	@Override
	public boolean open(Item item) {
		if (item != null)
			return state.open(item);
		return false;
	}

	@Override
	public boolean attack(Item item, GameObject gameObject) {
		gameObject.recieveDamage(0D);
		return true;
	}

	@Override
	public boolean look(GameObject gameObject) {
		if (gameObject != null)
			return state.look(gameObject);
		return false;
	}

	@Override
	public boolean unlock(Item toUnlock) {
		if (toUnlock != null)
			state.unlock(toUnlock);
		return false;
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
