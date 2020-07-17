package items.types;

import entities.Entity;
import items.Item;
import items.properties.Dispenser;
import tools.Gender;
import tools.Namber;

public class Source extends Item implements Dispenser {
	private Item content;

	public Source(Gender gender, Namber number, String name, String description, int price) {
		super(gender, number, name, description, price);
	}

	public Source(Gender gender, Namber number, String name, String description, Item content, int price) {
		super(gender, number, name, description, price);
		this.content = content;
	}

	public Item getContent() {
		return content;
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
		boolean result = this.content != null;
		entity.addItem(content);
		return result;
	}

}