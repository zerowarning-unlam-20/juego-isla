package items;

import java.util.ArrayList;
import java.util.List;

import interfaces.Unlockable;
import tools.Gender;

public class Container extends Item implements Unlockable {
	private List<Item> content;
	private boolean locked;

	public Container(int id, Gender gender, String name, String description, boolean locked) {
		super(id, gender, name, description, null);
		content = new ArrayList<>();
		this.locked = locked;
	}

	public Container(int id, Gender gender, String name, String description, boolean locked, List<Item> content) {
		super(id, gender, name, description, null);
		this.content = content;
		this.locked = locked;
	}

	public List<Item> getContent() {
		if (!locked) {
			return content;
		}
		return null;
	}

	public Item viewFromContent(String name) {
		Item result = null;
		if (!locked) {
			for (Item item : content) {
				if (item.getClass() == Container.class && !item.getName().contentEquals(name)) {
					result = ((Container) item).viewFromContent(name);
					if (result != null)
						break;
				}
				if (item.getName().contentEquals(name)) {
					result = item;
					break;
				}
			}
		}
		return result;
	}

	public Item getFromContent(String name) {
		Item result = null;
		if (!locked) {
			for (Item item : content) {
				if (item.getClass() == Container.class && !item.getName().contentEquals(name)) {
					result = ((Container) item).getFromContent(name);
					if (result != null)
						break;
				}
				if (item.getName().contentEquals(name)) {
					result = item;
					content.remove(item);
					break;
				}
			}
		}
		return result;
	}

	public Item getFromContent(int id) {
		Item result = null;
		if (!locked) {
			for (Item item : content) {
				if (item.getId() == id) {
					result = item;
					content.remove(item);
				}
			}
		}
		return result;
	}

	public Item getFirst() {
		Item result = null;
		if (!content.isEmpty())
			result = content.get(0);
		return result;
	}

	public void addContent(Item item) {
		content.add(item);
	}

	public void addAllContent(List<Item> content) {
		this.content.addAll(content);
	}

	public void setContent(List<Item> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		String result;
		if (!content.isEmpty())
			result = super.toString() + "\n tiene: " + content;
		else
			result = super.toString() + "\n vacio";
		return result;

	}

	public void empty() {
		content.clear();
	}

	@Override
	public boolean unlock() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isLocked() {
		return locked;
	}

}
