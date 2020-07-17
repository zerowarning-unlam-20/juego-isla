package items.types;

import entities.Entity;
import events.Event;
import items.Effect;
import items.Item;
import items.properties.Consumable;
import states.State;
import tools.Gender;
import tools.Namber;

public class Liquid extends Item implements Consumable {
	private Effect itemEffect;

	public Liquid(Gender gender, Namber number, String name, String description, int price, Effect itemEffect) {
		super(gender, number, name, description, price);
		this.itemEffect = itemEffect;
	}

	public Effect getItemEffect() {
		return itemEffect;
	}

	@Override
	public State consume(Entity entity) {
		Event event = entity.getGameManager().getGame().pullTrigger("_consume_" + this.name.toLowerCase(), entity);
		if (event == null || event.isNormalActionAllowed()) {
			return this.itemEffect.apply(entity);
		}
		return entity.getState();
	}
}
