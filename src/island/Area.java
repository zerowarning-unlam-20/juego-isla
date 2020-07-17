package island;

import java.util.HashMap;

import items.Item;
import tools.Gender;
import tools.Namber;

public class Area extends GameObject {

	public Area(Gender gender, Namber number, String name, String description, HashMap<String, Item> items) {
		super(gender, number, name, description);
		this.items = items;
	}

	private HashMap<String, Item> items;

	public Item getItem(String name) {
		return getItems().get(name);
	}

	public void removeItem(String name) {
		getItems().remove(name);
	}

	public String getContentString() {
		String message = "En " + this.getNormalName() + " hay ";
		String content = "";
		for (Item item : items.values()) {
			content += item.getOnlyName() + ", ";
		}
		if (content.isEmpty()) {
			content = "no hay nada";
		} else {
			content = content.substring(0, content.length() - 2);
			if (content.chars().filter(ch -> ch == ',').count() > 1)
				content = content.substring(0, content.lastIndexOf(",")) + " y"
						+ content.substring(content.lastIndexOf(",") + 1);
		}
		return message + content + ".";
	}

	public HashMap<String, Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.put(item.name.toLowerCase(), item);
	}

}
