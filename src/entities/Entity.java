package entities;

import java.util.LinkedList;
import java.util.List;

import island.GameObject;
import items.Item;
import items.Location;
import states.Normal;
import states.State;
import tools.Gender;

public abstract class Entity extends GameObject {
	protected Double health;
	protected Location location;
	protected List<Item> inventory;
	protected State state;

	public Entity(int id, Gender gender, String name, String description, Location location) {
		super(id, gender, name, description);
		this.location = location;
		this.state = new Normal(this);
		inventory = new LinkedList<Item>();
	}

	public boolean lookInventory() {
		return state.lookInventory();
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public Item removeItem(Item item) {
		inventory.remove(item);
		return item;
	}

	public boolean goTo(Location location) {
		return state.goTo(location);
	}

	public boolean grab(Item item) {
		return state.grab(item);
	}

	public boolean give(Item item, GameObject gameObject) {
		return state.give(item, gameObject);
	}

	public boolean unlock(Item toUnlock) {
		return state.unlock(toUnlock);
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public boolean drink(Item item) {
		state.drink(item);
		return item != null;
	}

	public boolean look(GameObject gameObject) {
		return state.look(gameObject);
	}

	public boolean open(Item item) {
		return state.open(item);
	}

	public boolean use(Item item, GameObject objective) {
		return objective.recieveObject(item);
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	public boolean lookAround() {
		return state.lookAround();
	}

	@Override
	public boolean recieveObject(GameObject object) {
		if (object != null && object.getClass() == Item.class) {
			this.inventory.add((Item) object);
			return true;
		}
		return false;
	}

	public boolean attack(Item item, GameObject gameObject) {
		gameObject.recieveDamage(0d);
		return true;
	}
}
