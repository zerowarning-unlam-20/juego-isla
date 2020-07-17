package items.types;

import entities.Entity;
import events.Event;
import items.Item;
import items.properties.Holdable;
import items.properties.Usable;
import states.Lost;
import tools.Gender;
import tools.MessageType;
import tools.Namber;

public class MapItem extends Item implements Usable, Holdable {

	public MapItem(Gender gender, Namber number, String name, String description, int price) {
		super(gender, number, name, description, price);
	}

	@Override
	public boolean use(Entity entity) {
		Event event = entity.getGameManager().getGame().pullTrigger("_use_" + this.name.toLowerCase(), entity);
		if (event == null || event.isNormalActionAllowed()) {
			entity.getGameManager().sendMessage(MessageType.CHARACTER, entity.getName(),
					"Estoy en " + entity.getLocation().getNormalName() + "\n" + entity.getLocation().lookAccesses());
			if (entity.getState().getClass().equals(Lost.class)) {
				((Lost) entity.getState()).setCompletelyLost(false);
			}
		}
		return true;
	}

}
