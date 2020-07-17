package items.types;

import entities.Entity;
import items.Item;
import items.properties.Holdable;
import items.properties.Usable;
import tools.Gender;
import tools.Namber;

public class SingleUseItem extends Item implements Usable, Holdable {

	public SingleUseItem(Gender gender, Namber number, String name, String description, int price, String unlocks) {
		super(gender, number, name, description, price);
	}

	@Override
	public boolean use(Entity entity) {
		entity.getGameManager().getGame().pullTrigger("_use_" + this.name.toLowerCase(), entity);
		return true;
	}

}
