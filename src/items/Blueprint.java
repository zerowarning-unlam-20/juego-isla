package items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tools.Gender;
import tools.ItemType;

public class Blueprint extends Item {
	private HashMap<String, Integer> requirements;
	private Item result;

	public Blueprint(int id, Gender gender, String name, String description, ItemType type,
			HashMap<String, Integer> requirements, Item produces) {
		super(id, gender, name, description, ItemType.BLUEPRINT);
		this.requirements = requirements;
		this.result = produces;
	}

	public HashMap<String, Integer> getRequirements() {
		return requirements;
	}

	public Item produce(List<Item> inventory) {
		HashMap<String, Integer> mapCount = new HashMap<String, Integer>();
		ArrayList<Item> usedItems = new ArrayList<>();
		for (Item item : inventory) {
			if (requirements.get(item.getName()) != null) {
				if (mapCount.get(item.getName()) == null) {
					usedItems.add(item);
					mapCount.put(item.getName(), 1);
				} else {
					usedItems.add(item);
					mapCount.put(item.getName(), 1 + mapCount.get(item.getName()));
				}
			}
		}
		if (mapCount.equals(requirements)) {
			for (Item item : usedItems) {
				inventory.remove(item);
			}
			return result;
		}
		return null;
	}

	public String getCreatedItemName() {
		return result.getName();
	}

}
