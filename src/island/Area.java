package island;

import java.util.HashMap;

import items.Item;
import tools.Gender;

public class Area extends GameObject {
	public Area(Gender gender, String name, String description, HashMap<String, Item> items) {
		super(gender, name, description);
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
		String message = description + ":\n";
		String content = "";
		for (Item item : items.values()) {
			content += item.getDescription() + ", ";
		}
		if (content.isEmpty()) {
			content = "No hay nada aqui";
		} else
			content = content.substring(0, content.length() - 2);
		return message + content;
	}

	public HashMap<String, Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.put(item.name.toLowerCase(), item);
	}

}
