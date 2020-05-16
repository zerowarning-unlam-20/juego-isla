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
				if (container.getContent().getName().contentEquals(itemName)) {
					((Container)i).empty();
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
				return a.open() ? "Se abrio: " + a.getName() : "No se abrio: " + a.getName();
			}
		}
		return "No se abri� nada";
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
				return acceso.getDescription() + ", lleva a " + acceso.getDestino().getName();
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
					if (!acceso.estaBloqueado()) {
						removeItem(item);
						return toUnlock + " se abri�";
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
		for (Location destination : location.getLocations()) {
			if (destination.getName().contentEquals(locationName)) {
				location = destination;
				return "me fui a " + location.getName();
			}
		}
		return "No pude ir a ningun lado";
	}

	@Override
	public String grab(String itemName) {
		List<Item> items = location.getItems();
		for (Item i : items) {
			if (i.getName().contentEquals(itemName)) {
				inventory.add(i);
				itemName = i.getDescription();
				location.removeItem(i);
				return "Se agarr� " + itemName;
			}
		}
		return "No se pudo agarrar nada";
	}
}
