package states;

import entities.Attack;
import entities.Entity;
import island.GameObject;
import items.Item;
import items.Location;
import items.Weapon;

public interface State {
	public boolean open(GameObject object);

	public boolean unlock(GameObject toUnlock);

	public boolean look(GameObject object);

	public boolean goTo(Location location);

	public boolean grab(Item item);

	public State drink(Item item);

	public boolean give(Item item, GameObject gameObject);

	public boolean lookAround();

	public boolean lookInventory();

	public boolean hit(Item item, GameObject gameObject);

	public State heal(Double points);

	public boolean attack(Weapon weapon, Entity objective);

	public State recieveAttack(Attack attack);
}
