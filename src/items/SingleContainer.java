package items;

import island.GameObject;
import tools.Gender;
import tools.ItemType;

public class SingleContainer extends Item {
	private Item content;
	private boolean infinite;

	public SingleContainer(int id, Gender gender, String name, String description, ItemType type) {
		super(id, gender, name, description, type);
	}

	public SingleContainer(int id, Gender gender, String name, String description, Item content, boolean visible,
			ItemType type, boolean infinite) {
		super(id, gender, name, description, type);
		this.infinite = infinite;
		this.content = content;
	}

	@Override
	public void use(Item objective) {
		objective.recieveObject(getContent());
	}

	@Override
	public boolean recieveObject(GameObject object) {
		if (object != null) {
			this.content = (Item) object;
			return true;
		}
		return false;
	}

	public Item getContent() {
		Item result = content;
		if (infinite) {
			content = null;
		}
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

	@Override
	public void recieveDamage(Double damage) {
		// TODO Auto-generated method stub

	}
}
