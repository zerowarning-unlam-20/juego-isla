package items.types;

import entities.Entity;
import items.Effect;
import items.Item;
import items.properties.Consumable;
import states.State;
import tools.Gender;

public class Liquid extends Item implements Consumable {
	private Effect itemEffect;

	public Liquid(Gender gender, String name, String description, int price, Effect itemEffect) {
		super(gender, name, description, price);
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
