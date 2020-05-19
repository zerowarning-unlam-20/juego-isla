package entities;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;

public class Container extends Item {
	private List<Item> content;

	public Container(int id, String name, String description, boolean visible) {
		super(id, name, description, visible);
	}

	public Container(int id, String name, String description, boolean visible, List<Item> content) {
		super(id, name, description, visible);
		this.content = content;
		if (content.size() == 1) {
			description = name + " con " + content.get(0).name;
		}
	}

	public List<Item> getContent() {
		return content;
	}

	public Item getFromContent(String name) {
		Item result = null;
		for (Item item : content) {
			if (item.getClass() == Container.class && !item.name.contentEquals(name)) {
				result = ((Container) item).getFromContent(name);
				if (result != null)
					break;
			}
			if (item.name.contentEquals(name)) {
				result = item;
				content.remove(item);
				break;
			}
		}
		return result;
	}

	public Item getFromContent(int id) {
		for (Item item : content) {
			if (item.id == id) {
				Item result = item;
				content.remove(item);
				return result;
			}
		}
		return null;
	}

	public void addContent(Item item) {
		if (content == null) {
			content = new ArrayList<Item>();
		}
		content.add(item);
	}

	public void setContent(List<Item> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		String result;
		if (content != null)
			result = super.toString() + "\n tiene: " + content;
		else
			result = super.toString() + "\n vacio";
		return result;

	}

	@Override
	public void use(Item objective) {
		objective.recieveObject(this);
	}

	@Override
	public void recieveObject(GameObject object) {
		addContent((Item) object);
	}

	public void empty() {
		content = null;
	}
}
