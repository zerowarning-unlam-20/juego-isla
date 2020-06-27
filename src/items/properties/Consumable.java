package items.properties;

import entities.Entity;
import states.State;

public interface Consumable {
	public State consume(Entity entity);
}
