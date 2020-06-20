package items.types;

import entities.Entity;
import items.Item;
import items.ItemEffect;
import items.properties.Holdable;
import items.properties.Usable;
import tools.Gender;

public class Consumable extends Item implements Holdable, Usable{
	private boolean needsContainer;
	private ItemEffect itemEffect;

	public Consumable(int id, Gender gender, String name, String description, boolean needsContainer,
			ItemEffect itemEffect) {
		super(id, gender, name, description);
		this.itemEffect = itemEffect;
		this.needsContainer = needsContainer;
	}

	public ItemEffect getItemEffect() {
		return itemEffect;
	}

	public void consume(Entity character) {
		itemEffect.apply(character);
	}

	public boolean needsContainer() {
		return needsContainer;
	}

	@Override
	public boolean use() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean use(Entity entity) {
		this.itemEffect.apply(entity);
		return true;
	}

}
