package states;

import island.GameObject;
import items.Item;
import items.Location;

public interface State {
	public boolean open(Item item);

	public boolean unlock(Item toUnlock);

	public boolean look(GameObject object);

	public boolean goTo(Location location);

	public boolean grab(Item item);

	public State drink(Item item);

	public boolean give(Item item, GameObject gameObject);

	public boolean lookAround();

	public boolean lookInventory();
	
	public boolean hit(Item item, GameObject gameObject);
	
	public State heal(Double points);

	public State recieveDamage(Double damage);
}
