package states;

import entities.Attack;
import entities.Entity;
import island.GameObject;
import island.Location;
import items.Item;
import items.types.Weapon;

public interface State {
	public boolean open(GameObject object);

	public boolean unlock(GameObject toUnlock, Item key);

	public boolean look(GameObject object);

	public boolean goTo(Location location);

	public boolean grab(Item item, Item content);

	public State drink(Item item);

	public boolean give(Item item, GameObject gameObject);

	public boolean lookAround();

	public boolean lookInventory();

	public boolean hit(Item item, GameObject gameObject);

	public void heal(Double points);

	public boolean attack(Weapon weapon, GameObject target);

	public State recieveAttack(Attack attack);

	public boolean talk(Entity other, String message);

	public boolean listen(Entity other, String message);

	public boolean use(Item item);

	public boolean read(Item item);

	public boolean create(Item item);

	public boolean inspect(Item item);

	public void lookState();
}
