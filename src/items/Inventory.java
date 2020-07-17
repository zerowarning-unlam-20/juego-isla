package items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Entity;
import items.properties.Consumable;
import items.properties.Holdable;
import items.properties.Usable;
import items.types.Resource;
import items.types.Weapon;
import tools.ObjectType;

public class Inventory {
	private HashMap<String, Item> items;

	public Inventory() {
		items = new HashMap<>();
	}

	public Inventory(HashMap<String, Item> items) {
		this.items = items;
	}

	public Collection<Item> getItems() {
		return items.values();
	}

	public void addItem(Item item) {
		items.put(item.getName().toLowerCase(), item);
	}

	public void addResource(Resource resource) {
		Resource res = (Resource) items.get(resource.getName());
		if (res != null) {
			res.setQuantity(res.getQuantity() + resource.getQuantity());
		} else
			items.put(resource.getName().toLowerCase(), resource);
	}

	public void addResource(String name, int quantity) {
		Resource res = (Resource) items.get(name);
		if (res != null) {
			res.setQuantity(res.getQuantity() + quantity);
		}
	}

	public void removeResource(String name, int quantity) {
		Resource res = (Resource) items.get(name);
		if (res != null) {
			res.setQuantity(res.getQuantity() + quantity);
			if (res.getQuantity() <= 0) {
				items.remove(res.getName().toLowerCase());
			}
		}
	}

	public void removeItem(Item item) {
		items.remove(item.getName().toLowerCase());
	}

	public Item removeItem(String name) {
		return items.remove(name.toLowerCase());
	}

	public List<Item> findItemsOfType(ObjectType type) {
		ArrayList<Item> result = new ArrayList<>();
		for (Item item : items.values()) {
			if (item.getClass().equals(type.getClass())) {
				result.add(item);
			}
		}
		return result;
	}

	public String showItems() {
		String result = "";
		for (Item item : items.values()) {
			if (item instanceof Resource) {
				result += " - " + item.getDescription() + " x" + ((Resource) item).getQuantity() + "\n";
			} else {
				result += " - " + item.getDescription() + "\n";
			}
		}
		return result.contentEquals("") ? "el inventario esta vacio" : result.substring(0, result.length() - 1);
	}

	public Item getItem(String name) {
		return items.get(name);
	}

	public String getItemUses(String name) {
		String result = "";
		Item item = items.get(name);
		if (item != null) {
			result = "se puede";
			if (item instanceof Holdable) {
				result += "agarrar, ";
			}
			if (item instanceof Usable) {
				result += "usar, ";
			}
			if (item instanceof Consumable) {
				result += "consumir, ";
			}
			if (item instanceof Readable) {
				result += "leer, ";
			}
		} else
			return "Hay ningun item con ese nombre";
		return result.contentEquals("se puede") ? "no se puede hacer nada" : result.substring(0, result.length() - 2);
	}

	public void giveAllItems(Entity entity) {
		for (Item item : items.values()) {
			entity.addItem(item);
		}
		items.clear();
	}

	public void recieveItem(Item item) {
		if (item instanceof Item) {
			this.addItem(item);
		} else if (item instanceof Resource) {
			this.addResource((Resource) item);
		}
	}

	public void clear() {
		items.clear();
	}

	public void removeResources(HashMap<String, Integer> resources) {
		for (Map.Entry<String, Integer> entry : resources.entrySet()) {
			removeResource(entry.getKey(), entry.getValue());
		}
	}

	public String showItemsToSell() {
		String result = "";
		for (Item item : items.values()) {
			if (item.getValue() >= 0) {
				if (item instanceof Resource) {
					result += " - " + item.getDescription() + " x" + ((Resource) item).getQuantity() + " - "
							+ item.getValue() + "\n";
				} else {
					result += " - " + item.getDescription() + " - " + item.getValue() + "\n";
				}
			}
		}
		return result.contentEquals("") ? "no tengo nada para vender por el momento"
				: result.substring(0, result.length() - 1);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

	public String getBestWeapon() {
		Weapon best = null;
		for (Item i : getItems()) {
			if (i instanceof Weapon) {
				if (best == null) {
					best = (Weapon) i;
				} else if (best.getDamage() < ((Weapon) i).getDamage()) {
					best = (Weapon) i;
				}
			}
		}
		return best != null ? best.getName().toLowerCase() : null;
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}
}
