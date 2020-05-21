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

	public void lookInventory() {
		state.lookInventory();
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public Item removeItem(Item item) {
		inventory.remove(item);
		return item;
	}

	public void goTo(Location location) {
		state.goTo(location);
	}

	public void grab(Item item) {
		state.grab(item);
	}

	public void give(Item item, GameObject gameObject) {
		state.give(item, gameObject);
	}

	public void unlock(Item toUnlock) {
		state.unlock(toUnlock);
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void drink(Item item) {
		state.drink(item);
	}

	public void look(GameObject gameObject) {
		state.look(gameObject);
	}

	public void open(Item item) {
		state.open(item);
	}

	public void use(Item item, GameObject objective) {
		objective.recieveObject(item);
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	public void lookAround() {
		state.lookAround();
	}

	@Override
	public boolean recieveObject(GameObject object) {
		if (object != null && object.getClass() == Item.class) {
			this.inventory.add((Item) object);
			return true;
		}
		return false;
	}

	public void attack(Item item, GameObject gameObject) {
		gameObject.recieveDamage(0d);
	}
}
