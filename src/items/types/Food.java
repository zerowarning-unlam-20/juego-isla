package items.types;

import entities.Entity;
import items.Item;
import items.Effect;
import items.properties.Consumable;
import states.State;
import tools.Gender;

public class Food extends Item implements Consumable {
	private Effect itemEffect;

	public Food(Gender gender, String name, String description, Effect itemEffect) {
		super(gender, name, description);
		this.itemEffect = itemEffect;
	}

	public Effect getItemEffect() {
		return itemEffect;
	}

	@Override
	public State consume(Entity entity) {
		return this.itemEffect.apply(entity);
	}

}
