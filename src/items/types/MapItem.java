package items.types;

import entities.Entity;
import items.Item;
import items.ItemEffect;
import items.properties.Holdable;
import items.properties.Usable;
import states.Dead;
import states.Lost;
import states.Normal;
import tools.Gender;
import tools.MessageType;

public class MapItem extends Item implements Usable, Holdable {
	private ItemEffect itemEffect;

	public MapItem(int id, Gender gender, String name, String description) {
		super(id, gender, name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean use() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean use(Entity entity) {
		Class<?> previousState = entity.getState().getClass();
		this.itemEffect.apply(entity);
		if (previousState.equals(Lost.class) && entity.getState() instanceof Normal)
			entity.getGameManager().sendMessage(MessageType.CHARACTER, entity,
					"Ya no estoy tan perdid" + entity.getTermination());
		if (!previousState.equals(Dead.class))
			entity.getGameManager().sendMessage(MessageType.CHARACTER, entity, entity.getLocation().lookAround());
		return true;
	}

}
