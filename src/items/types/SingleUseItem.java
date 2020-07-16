package items.types;

import entities.Entity;
import items.Item;
import items.properties.Holdable;
import items.properties.Usable;
import tools.Gender;

public class SingleUseItem extends Item implements Usable, Holdable {

	public SingleUseItem(Gender gender, String name, String description, int price, String unlocks) {
		super(gender, name, description, price);
	}

	@Override
	public boolean use(Entity entity) {
		entity.getGameManager().getGame().pullTrigger(this.name + "_" + "use");
		return true;
	}

}
