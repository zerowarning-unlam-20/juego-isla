package entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import island.GameObject;
import island.Location;
import items.Item;
import items.Weapon;
import manager.GameManager;
import states.Dead;
import states.Normal;
import states.State;
import tools.DamageType;
import tools.Gender;
import tools.MessageType;

public abstract class Entity extends GameObject {
	protected GameManager gameManager;
	protected Double baseHealth;
	protected Double health;
	protected Location location;
	protected List<Item> inventory;
	protected HashMap<DamageType, Double> weakAndRes;
	protected int initialLocation;
	protected State state;

	public Entity(GameManager gameManager, int id, Gender gender, String name, String description, Location location) {
		super(id, gender, name, description);
		this.baseHealth = 100d;
		this.health = 100d;
		this.location = location;
		this.state = new Normal(this);
		this.gameManager = gameManager;
		inventory = new LinkedList<Item>();
		weakAndRes = new HashMap<DamageType, Double>();
	}

	public Entity(GameManager gameManager, int id, Gender gender, String name, String description, Location location,
			List<Item> inventory, int initialLocation) {
		super(id, gender, name, description);
		this.baseHealth = 100d;
		this.health = 100d;
		this.gameManager = gameManager;
		this.location = location;
		this.state = new Normal(this);
		this.inventory = inventory;
		weakAndRes = new HashMap<DamageType, Double>();
		this.initialLocation = initialLocation;
	}

	public Entity(GameManager gameManager, int id, Gender gender, String name, String description, List<Item> inventory,
			int initialLocation) {
		super(id, gender, name, description);
		this.baseHealth = 100d;
		this.health = 100d;
		this.gameManager = gameManager;
		this.state = new Normal(this);
		this.inventory = inventory;
		this.initialLocation = initialLocation;
		weakAndRes = new HashMap<DamageType, Double>();
	}

	public void linkToManager(GameManager gameManager) {
		this.gameManager = gameManager;
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

	public HashMap<String, Item> getInventoryStringMap() {
		HashMap<String, Item> map = new HashMap<>();
		for (Item item : inventory) {
			map.put(item.getName().toLowerCase(), item);
		}
		return map;
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
		state = state.drink(item);
		if (item != null || state.getClass() == Dead.class) {
			return false;
		}
		return true;
	}

	public boolean look(GameObject gameObject) {
		return state.look(gameObject);
	}

	public boolean open(GameObject item) {
		return state.open(item);
	}

	public void addItem(Item item) {
		gameManager.sendMessage(MessageType.EVENT, this, "Recibido: " + item.getName());
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
		state = state.recieveAttack(attack);
	}

	public boolean create(Item item) {
		return state.create(item);
	}

	public void onDeath(Attack attack) {
		for (Item item : inventory) {
			attack.getAttacker().addItem(item);
		}
	}

	public boolean talk(Entity other, String message) {
		return state.talk(other, message);
	}

	public boolean listen(Entity other, String message) {
		return state.listen(other, message);
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public Double getBaseHealth() {
		return baseHealth;
	}

	public int getInitialLocation() {
		return initialLocation;
	}
}
