package states;

import island.GameObject;
import items.Item;
import items.Location;

public interface State {
	public void open(Item item);

	public void unlock(Item toUnlock);

	public void look(GameObject object);

	public void goTo(Location location);

	public void grab(Item item);

	public State drink(Item item);

	public void give(Item item, GameObject gameObject);

	public void lookAround();

	public void lookInventory();
	
	public void hit(Item item, GameObject gameObject);
	
	public State heal(Double points);

	public State recieveDamage(Double damage);
}
