package items.properties;

import items.Item;

public interface Unlockable {
	public void unlock();
	public boolean unlock(Item keyItem);
}
