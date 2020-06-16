package items;

import entities.Entity;
import tools.Gender;
import tools.ItemType;

public class Consumable extends Item {
	private boolean needsContainer;
	private ItemEffect itemEffect;
	public Consumable(int id, Gender gender, String name, String description,boolean needsContainer, ItemEffect itemEffect) {
		super(id, gender, name, description, ItemType.CONSUMABLE);
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
	
}
