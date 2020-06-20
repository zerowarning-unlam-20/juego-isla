package items.types;

import entities.Attack;
import items.Item;
import items.properties.Attackable;
import items.properties.Holdable;
import tools.Gender;

public class Container extends Item implements Holdable, Attackable {
	private Item content;

	public Container(int id, Gender gender, String name, String description) {
		super(id, gender, name, description);
	}

	public Container(int id, Gender gender, String name, String description, Item content, boolean infinite) {
		super(id, gender, name, description);
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
	public boolean recieveAttack(Attack attack) {
		return true;
	}

}
