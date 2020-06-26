package items.types;

import entities.Entity;
import items.Item;
import items.properties.Holdable;
import items.properties.Usable;
import states.Lost;
import tools.Gender;
import tools.MessageType;

public class MapItem extends Item implements Usable, Holdable {

	public MapItem(Gender gender, String name, String description) {
		super(gender, name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean use(Entity entity) {
		entity.getGameManager().sendMessage(MessageType.CHARACTER, entity.getName(),
				"Estoy en " + entity.getLocation().getSingularName() + "\n" + entity.getLocation().lookAccesses());
		if (entity.getState().getClass().equals(Lost.class)) {
			((Lost) entity.getState()).setCompletelyLost(false);
		}
		return true;
	}

}
