package entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import island.GameObject;
import items.Item;
import items.Location;
import items.Weapon;
import manager.GameManager;
import states.Normal;
import states.State;
import tools.DamageType;
import tools.Gender;
import tools.MessageType;

public abstract class Entity extends GameObject {
	protected Double health;
	protected Location location;
	protected List<Item> inventory;
	protected HashMap<DamageType, Double> weakAndRes;
	protected State state;

	public Entity(int id, Gender gender, String name, String description, Location location) {
		super(id, gender, name, description);
		this.health = 100d;
		this.location = location;
		this.state = new Normal(this);
		inventory = new LinkedList<Item>();
		weakAndRes = new HashMap<DamageType, Double>();
	}
	
	public Entity(int id, Gender gender, String name, String description, Location location, List<Item> inventory) {
		super(id, gender, name, description);
		this.health = 100d;
		this.location = location;
		this.state = new Normal(this);
		this.inventory = inventory;
		weakAndRes = new HashMap<DamageType, Double>();
	}

	public void addWeakOrRes(DamageType type, Double multiplier) {
		weakAndRes.put(type, multiplier);
	}

	public void removeWeakOrRes(DamageType type, Double multiplier) {
		weakAndRes.remove(type);
	}

	public Double getWeaknessModifier(DamageType damageType) {
		Double modifier = weakAndRes.get(damageType);
		return (modifier == null) ? 1 : modifier;
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

	public boolean unlock(GameObject toUnlock) {
		return state.unlock(toUnlock);
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public Double getHealth() {
		return health;
	}

	public boolean drink(Item item) {
		state.drink(item);
		return item != null;
	}

	public boolean look(GameObject gameObject) {
		return state.look(gameObject);
	}

	public boolean open(GameObject item) {
		return state.open(item);
	}

	public void addItem(Item item) {
		GameManager.sendMessage(MessageType.EVENT, this, "Recibido: " + item.getName());
		inventory.add(item);
	}

	public boolean lookAround() {
		return state.lookAround();
	}

	public void setHealth(Double health) {
		this.health = health;
	}

	public boolean attack(Weapon weapon, Entity objective) {
		return state.attack(weapon, objective);
	}

	public void recieveAttack(Attack attack) {
		state.recieveAttack(attack);
	}

	public void onDeath(Attack attack) {
		for (Item item : inventory) {
			attack.getAttacker().addItem(item);
		}
	}
}
