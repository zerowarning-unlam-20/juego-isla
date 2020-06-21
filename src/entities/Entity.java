package entities;

import java.util.HashMap;

import island.GameObject;
import island.Location;
import items.Access;
import items.Item;
import items.properties.Attackable;
import items.types.Weapon;
import manager.GameManager;
import states.Dead;
import states.Normal;
import states.State;
import tools.DamageType;
import tools.Gender;
import tools.MessageType;

public abstract class Entity extends GameObject implements Attackable {
	protected GameManager gameManager;
	protected Double baseHealth;
	protected Double health;
	protected Location location;
	protected HashMap<String, Item> inventory;
	protected HashMap<DamageType, Double> weakAndRes;
	protected String initialLocation;
	protected State state;

	public Entity(GameManager gameManager, Gender gender, String name, String description, Location location,
			HashMap<String, Item> inventory, String initialLocation) {
		super(gender, name, description);
		this.baseHealth = 100d;
		this.health = 100d;
		this.gameManager = gameManager;
		this.location = location;
		this.state = new Normal(this);
		this.inventory = inventory;
		weakAndRes = new HashMap<DamageType, Double>();
		this.initialLocation = initialLocation;
	}

	public void setState(State state) {
		this.state = state;
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

	public HashMap<String, Item> getInventory() {
		return inventory;
	}

	public Item removeItem(Item item) {
		inventory.remove(item.getName().toLowerCase());
		return item;
	}

	public boolean goTo(Location location) {
		return state.goTo(location);
	}

	public boolean grab(Item item, Item content) {
		return state.grab(item, content);
	}

	public boolean give(Item item, GameObject gameObject) {
		return state.give(item, gameObject);
	}

	public boolean unlock(GameObject toUnlock, Item key) {
		return state.unlock(toUnlock, key);
	}

	public boolean read(Item item) {
		return state.read(item);
	}

	public void setLocation(Location location) {
		this.location = location;
		if (location.getEntities() != null) {
			location.getEntities().put(this.name, this);
		}
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
		inventory.put(item.getName().toLowerCase(), item);
	}

	public boolean lookAround() {
		return state.lookAround();
	}

	public void setHealth(Double health) {
		this.health = health;
	}

	public boolean attack(Weapon weapon, GameObject target) {
		return state.attack(weapon, target);
	}

	@Override
	public boolean recieveAttack(Attack attack) {
		state = state.recieveAttack(attack);
		return true;
	}

	public boolean create(Item item) {
		return state.create(item);
	}

	public void onDeath(Attack attack) {
		for (Item i : inventory.values()) {
			attack.getAttacker().addItem(i);
		}
		inventory.clear();
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

	public String getInitialLocation() {
		return initialLocation;
	}

	public State getState() {
		return state;
	}

	public boolean goTo(String name) {
		boolean result = false;
		Access access = location.getAccesses().get(name);
		if (access != null && access.getDestination() != null) {
			result = state.goTo(access.getDestination());
		} else
			state.goTo(null);
		return result;
	}

	public boolean use(Item item) {
		return state.use(item);
	}

	public void lookState() {
		state.lookState();
	}

	public void inspect(Item result) {
		state.inspect(result);
	}

}
