package items.types;

import entities.Entity;
import items.Item;
import items.properties.Usable;
import tools.Gender;

public class SingleUseItem extends Item implements Usable {

	public SingleUseItem(Gender gender, String name, String description, String unlocks) {
		super(gender, name, description);
	}

	@Override
	public boolean use(Entity entity) {
		// entity.getGameManager().getGame().pullTrigger(this.name + "_" + "use");
		return true;
	}

}