package entities;

import java.util.HashMap;

import island.GameObject;
import island.Location;
import items.Item;
import items.properties.Attackable;
import manager.GameManager;
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

	public boolean grab(String item) {
		return state.grab(item);
	}

	public boolean grab(String source, String name) {
		return state.grab(source, name);
	}

	public boolean grab(String name, String source, String container) {
		return state.grab(name, source, container);
	}

	public boolean unlock(String toUnlock, String keyName) {
		return state.unlock(toUnlock, keyName);
	}

	public boolean read(String itemName) {
		return state.read(itemName);
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

	public State drink(String name, String dispenserName) {
		return state.drink(name, dispenserName);
	}

	public boolean look(String objectName) {
		return state.look(objectName);
	}

	public boolean open(String item) {
		return state.open(item);
	}

	public void addItem(Item item) {
		gameManager.sendMessage(MessageType.EVENT, this.name, "Recibido: " + item.getName());
		inventory.put(item.getName().toLowerCase(), item);
	}

	public boolean lookAround() {
		return state.lookAround();
	}

	public void setHealth(Double health) {
		if (this.health > health) {
			this.getGameManager().sendMessage(MessageType.EVENT, name,
					name + " recibio " + (this.health - health) + " de daño");
		} else if (this.health < health) {
			this.getGameManager().sendMessage(MessageType.EVENT, name,
					name + " recupero " + (health - this.health) + " de salud");
		} else
			this.getGameManager().sendMessage(MessageType.EVENT, name, "No veo ningun cambio");
		this.health = health;
	}

	public boolean attack(String weapon, String target) {
		return state.attack(weapon, target);
	}

	@Override
	public boolean recieveAttack(Attack attack) {
		state = state.recieveAttack(attack);
		return true;
	}

	public void onDeath(Attack attack) {
		for (Item i : inventory.values()) {
			attack.getAttacker().addItem(i);
		}
		inventory.clear();
	}

	public boolean talk(String other, String message) {
		return state.talk(other, message);
	}

	public boolean listen(String other, String message) {
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
		return state.goTo(name);
	}

	public boolean use(String itemName) {
		return state.use(itemName);
	}

	public void lookState() {
		state.lookState();
	}

	public boolean inspect(String name) {
		return state.inspect(name);
	}
}
