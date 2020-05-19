package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import states.Normal;
import states.State;

public class UserCharacter extends Entity {
	private Location location;
	private List<Item> inventory;

	public UserCharacter(Location locacion) {
		super(0, "Carlos", "Tachero", true);
		states = new ArrayList<State>();
		this.states.add(new Normal());
		setInventory(new LinkedList<Item>());
		this.location = locacion;
	}

	public String lookAround() {
		return location.getDescription();
	}

	public String drink(String itemName) {
		for (Item i : inventory) {
			if (i.getClass() == Container.class) {
				Container container = (Container) i;
				if (container.getContent().get(0) != null
						&& container.getContent().get(0).getName().contentEquals(itemName)) {
					((Container) i).empty();
					return "Tomando " + itemName + " de " + container.getName();
				}
			}
		}
		return "No hay nada para tomar";
	}

	@Override
	public String open(String itemName) {
		for (Access a : location.getAccesses()) {
			if (a.getName().contentEquals(itemName) || a.getDescription().contentEquals(itemName)) {
				boolean possible = a.open();
				if (possible == false)
					if (a.isOpened())
						return "Ya está abierto";
					else
						return "No se pudo abrir";
				else
					return "Se abrió " + a.getName();
			}
		}
		return "No se abrió nada";

	}

	public String close() {
		return null;
	}

	@Override
	public String look(String itemName) {
		if (itemName.contentEquals("alrededor") || itemName.contentEquals(location.getName())) {
			return lookAround();
		}
		if (itemName.contentEquals("inventario")) {
			return lookInventory();
		}
		for (Item item : inventory) {
			if (item.getName().contentEquals(itemName)) {
				return item.getDescription();
			}
		}
		for (Access acceso : location.getAccesses()) {
			if (acceso.getName().contentEquals(itemName) || acceso.getDescription().contentEquals(itemName)) {
				return acceso.getDescription() + ", lleva a " + acceso.getDestination().getName() + ". \n" + "Está "
						+ acceso.getStatus();
			}
		}

		for (Item item : location.getItems()) {
			if (item.getName().contentEquals(itemName) || item.getDescription().contentEquals(itemName)) {
				return item.getDescription();
			}
		}

		return "No pude ver nada";
	}

	@Override
	public String unlock(String toUnlock) {
		for (Item item : inventory)
			for (Access acceso : location.getAccesses())
				if (acceso.getName().contentEquals(toUnlock)) {
					acceso.recieveObject(item);
					if (!acceso.isLocked()) {
						removeItem(item);
						return toUnlock + " se desbloqueó";
					}
				}
		return "No se pudo desbloquear nada";
	}

	public String lookInventory() {
		return getInventory().isEmpty() ? "Inventario vacio" : "En el inventario hay " + getInventory().toString();
	}

	@Override
	public String toString() {
		return super.toString() + "\n Locacion=" + location + "\n Estados=" + states.toString() + "\n Inventario="
				+ getInventory();
	}

	public void use(Item item) {

	}

	public Location getLocation() {
		return location;
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void setInventory(List<Item> inventario) {
		this.inventory = inventario;
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	public Item removeItem(Item item) {
		inventory.remove(item);
		return item;
	}

	public Item removeItem(int idItem) {
		Item removed = null;
		for (Item i : inventory) {
			if (i.getId() == idItem) {
				removed = i;
				inventory.remove(i);
				break;
			}
		}
		return removed;
	}

	@Override
	public void recieveObject(GameObject object) {
		// TODO Auto-generated method stub

	}

	@Override
	public String goTo(String locationName) {
		for (Access access : location.getAccesses()) {
			if (access.getDestination().getName().contentEquals(locationName) && access.isOpened()) {
				location = access.getDestination();
				return "me fui a " + location.getName();
			}
		}
		return "No pude ir a ningun lado";
	}

	@Override
	public String grab(String itemName) {
		List<Item> items = location.getItems();
		String result = "No se pudo agarrar nada";
		for (Item i : items) {
			if (i.getClass() == Container.class && !i.name.contentEquals(itemName)) {
				Item item = ((Container) i).getFromContent("itemName");
				if (item != null) {
					inventory.add(item);
					result = "Se agarró " + itemName;
					break;
				}
			} else if (i.getName().contentEquals(itemName)) {
				inventory.add(i);
				itemName = i.getDescription();
				location.removeItem(i);
				result = "Se agarró " + itemName;
				break;
			}
		}
		return result;
	}
}
