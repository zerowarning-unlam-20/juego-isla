package items.types;

import entities.Entity;
import items.Item;
import items.properties.Consumable;
import items.properties.Dispenser;
import items.properties.Holdable;
import tools.Gender;
import tools.Namber;

public class Bottle extends Item implements Holdable, Dispenser {
	private Item content;

	public Bottle(Gender gender, Namber number, String name, String description, int price) {
		super(gender, number, name, description, price);
	}

	public Bottle(Gender gender, Namber number, String name, String description, int price, Item content,
			boolean infinite) {
		super(gender, number, name, description, price);
		this.content = content;
	}

	public Item getContent() {
		Item result = content;
		content = null;
		return result;
	}

	public String getStatus() {
		String result = null;
		if (content == null) {
			result = "vaci" + gender.getTermination(number);
		}
		return result;
	}

	public Item empty() {
		Item result = content;
		content = null;
		return result;
	}

	public void setContent(Item content) {
		this.content = content;
	}

	public boolean isEmpty() {
		return content == null;
	}

	@Override
	public boolean giveItems(Entity entity) {
		if (content == null)
			return false;
		((Consumable) content).consume(entity);
		return true;
	}
}
