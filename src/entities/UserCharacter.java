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
		String retorno = null;

		for (Item i : inventory) {
			if (i.getClass() == Container.class) {
				Container container = (Container) i;
				if (container.getContent().getName().contentEquals(itemName)) {
					((Container) i).empty();
					retorno = "Tomando " + itemName + " de " + container.getName();
					break;
				}
			}
		}

		return (retorno != null) ? retorno : "No hay nada para tomar";
	}

	@Override
	public String open(String itemName) {
		String retorno = null;

		for (Access a : location.getAccesses()) {
			if (a.getName().contentEquals(itemName) || a.getDescription().contentEquals(itemName)) {
				retorno = a.open() ? "Se abrio: " + a.getName() : "No se abrio: " + a.getName();
				break;
			}
		}
		return (retorno != null) ? retorno : "No se abrió nada";
	}

	public String close() {
		return null;
	}

	@Override
	public String look(String itemName) {
		String retorno = null;

		if (itemName.contentEquals("alrededor") || itemName.contentEquals(location.getName())) {
			retorno = lookAround();
		} else {
			if (itemName.contentEquals("inventario")) {
				retorno = lookInventory();
			} else {
				for (Item item : inventory) {
					if (item.getName().contentEquals(itemName)) {
						retorno = item.getDescription();
						break;
					}
				}
				if (retorno == null) {
					for (Access acceso : location.getAccesses()) {
						if (acceso.getName().contentEquals(itemName)
								|| acceso.getDescription().contentEquals(itemName)) {
							retorno = acceso.getDescription() + ", lleva a " + acceso.getDestino().getName();
							break;
						}
					}
				}
				if (retorno == null) {
					for (Item item : location.getItems()) {
						if (item.getName().contentEquals(itemName) || item.getDescription().contentEquals(itemName)) {
							retorno = item.getDescription();
							break;
						}
					}
				}
			}
		}
		return (retorno!=null)? retorno : "No pude ver nada";
	}

	@Override
	public String unlock(String toUnlock) {
		String retorno = null;
		
		for (Item item : inventory) {
			for (Access acceso : location.getAccesses())
				if (acceso.getName().contentEquals(toUnlock)) {
					acceso.recieveObject(item);
					if (!acceso.estaBloqueado()) {
						removeItem(item);
						retorno = toUnlock + " se abrió";
						break;
					}
				}
			if(retorno != null) break;
		}
		
		return (retorno!=null)? retorno : "No se pudo desbloquear nada";
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
		String retorno = null;
		
		for (Location destination : location.getLocations()) {
			if (destination.getName().contentEquals(locationName)) {
				location = destination;
				retorno = "me fui a " + location.getName();
				break;
			}
		}
		return (retorno!=null)? retorno : "No pude ir a ningun lado";
	}

	@Override
	public String grab(String itemName) {
		String retorno = null;
		
		List<Item> items = location.getItems();
		for (Item i : items) {
			if (i.getName().contentEquals(itemName)) {
				inventory.add(i);
				itemName = i.getDescription();
				location.removeItem(i);
				retorno = "Se agarró " + itemName;
				break;
			}
		}
		
		return (retorno!=null)? retorno : "No se pudo agarrar nada";
	}
}
