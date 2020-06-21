package items.types;

import entities.Entity;
import items.Item;
import items.properties.Dispenser;
import tools.Gender;

public class Source extends Item implements Dispenser {
	private Item content;

	public Source(Gender gender, String name, String description) {
		super(gender, name, description);
	}

	public Source(Gender gender, String name, String description, Item content) {
		super(gender, name, description);
		this.content = content;
	}

	public Item getContent() {
		return content;
	}

	public String getStatus() {
		String result = null;
		if (content == null) {
			result = "vaci" + gender.getTermination();
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