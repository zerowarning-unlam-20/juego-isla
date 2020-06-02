package items;

import tools.Gender;

public class SingleContainer extends Item {
	private Item content;
	private boolean infinite;

	public SingleContainer(int id, Gender gender, String name, String description) {
		super(id, gender, name, description, null);
	}

	public SingleContainer(int id, Gender gender, String name, String description, Item content, boolean visible, boolean infinite) {
		super(id, gender, name, description, null);
		this.infinite = infinite;
		this.content = content;
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

}
