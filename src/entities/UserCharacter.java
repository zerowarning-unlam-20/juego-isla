package entities;

import java.util.LinkedList;
import java.util.List;

import states.Normal;
import states.State;

public class UserCharacter extends GameObject {
	private Location location;
	private State state;
	private List<Item> inventory;

	public UserCharacter(Location locacion) {
		super(0, "Carlos", "Tachero", true);
		this.state = new Normal();
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
					inventory.remove(i);
					return "Tomando " + itemName + " de " + container.getName();
				}
			}
		}
		return "No hay nada para tomar";
	}

	public String open(String itemName) {
		for (Access a : location.getAccesses()) {
			if (a.getName().contentEquals(itemName) || a.getDescription().contentEquals(itemName)) {
				return a.open() ? "Se abrio: " + a.getName() : "No se abrio: " + a.getName();
			}
		}
		return "No se abrió nada";
	}

	public String close() {
		return null;
	}

	public String look(String object) {
		if (object.contentEquals("alrededor") || object.contentEquals(location.getName())) {
			return lookAround();
		}
		if (object.contentEquals("inventario")) {
			return lookInventory();
		}
		for (Item item : inventory) {
			if (item.getName().contentEquals(object)) {
				return item.getDescription();
			}
		}
		for (Access acceso : location.getAccesses()) {
			if (acceso.getName().contentEquals(object) || acceso.getDescription().contentEquals(object)) {
				return acceso.getDescription() + ", lleva a " + acceso.getDestino().getName();
			}
		}

		for (Item item : location.getItems()) {
			if (item.getName().contentEquals(object) || item.getDescription().contentEquals(object)) {
				return item.getDescription();
			}
		}

		return "No pude ver nada";
	}

	public String unlock(String toUnlock) {
		for (Item item : inventory)
			for (Access acceso : location.getAccesses())
				if (acceso.getName().contentEquals(toUnlock)) {
					acceso.recieveObject(item);
					if (!acceso.estaBloqueado()) {
						removeItem(item);
						return toUnlock + " se abrió";
					}
				}
		return "No se pudo desbloquear nada";
	}

	public String lookInventory() {
		return getInventory().isEmpty() ? "Inventario vacio" : "En el inventario hay " + getInventory().toString();
	}

	@Override
	public String toString() {
		return "Nombre=" + name + "\n Locacion=" + location + "\n Estado=" + state + "\n Inventario=" + getInventory();
	}

	public void changeLocation(Location destination) {
		if (location.getLocations().contains(destination)) {
			if (!destination.isLocked()) {
				this.location = destination;
				System.out.println("Ubicacion cambiada");
				return;
			}
		}
		System.out.println("Ubicacion no cambiada");
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

	public String goTo(String locationName) {
		for (Location destination : location.getLocations()) {
			if (destination.getName().contentEquals(locationName)) {
				location = destination;
				return "me fui a " + location.getName();
			}
		}
		return "No pude ir a ningun lado";
	}

	public String grab(String itemName) {
		List<Item> items = location.getItems();
		for (Item i : items) {
			if (i.getName().contentEquals(itemName)) {
				inventory.add(i);
				itemName = i.getDescription();
				location.removeItem(i);
				return "Se agarró " + itemName;
			}
		}
		return "No se pudo agarrar nada";
	}
}
