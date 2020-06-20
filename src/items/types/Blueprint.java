package items.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Entity;
import items.Item;
import items.properties.Holdable;
import items.properties.Inspectable;
import items.properties.Usable;
import items.properties.Readablel;
import tools.Gender;
import tools.MessageType;
import tools.ResourceType;

public class Blueprint extends Item implements Inspectable, Usable, Readablel, Holdable {
	private HashMap<ResourceType, Integer> requirements;
	private Item result;

	public Blueprint(int id, Gender gender, String name, String description,
			HashMap<ResourceType, Integer> requirements, Item produces) {
		super(id, gender, name, description);
		this.requirements = requirements;
		this.result = produces;
	}

	public String getRequirements() {
		String result = "Se necesita: ";
		for (Map.Entry<ResourceType, Integer> entry : requirements.entrySet()) {
			result += entry.getKey().getValue() + ": x" + entry.getValue() + "\n";
		}
		return result;
	}

	private Item produce(List<Item> inventory) {
		HashMap<ResourceType, Integer> mapCount = new HashMap<ResourceType, Integer>();
		ArrayList<Item> usedItems = new ArrayList<>();
		for (Item item : inventory) {
			if (item instanceof Resource) {
				Resource res = (Resource) item;
				if (requirements.get(res.getResourceType()) != null) {
					if (mapCount.get(res.getResourceType()) == null) {
						usedItems.add(item);
						mapCount.put(res.getResourceType(), 1);
					} else {
						usedItems.add(item);
						mapCount.put(res.getResourceType(), 1 + mapCount.get(res.getResourceType()));
					}
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

	@Override
	public boolean use() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean use(Entity entity) {
		Item result = this.produce(entity.getInventory());
		if (result != null) {
			entity.addItem(result);
			entity.removeItem(this);
			entity.getGameManager().sendMessage(MessageType.EVENT, entity, "Ahora tenes: " + result.getDescription());
		}
		else {
			entity.getGameManager().sendMessage(MessageType.EVENT, entity, "No tenes los elementos necesarios");
		}
		return result != null;
	}

	@Override
	public String read(boolean visible) {
		return visible ? getRequirements() : "No se ve nada";
	}

}
