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
		String stringToReturn = null;

		for (Item i : inventory) {

			if (i.getClass() == Container.class) {
				Container container = (Container) i;

				if (container.getContent().getName().contentEquals(itemName)) {

					((Container) i).empty();
					stringToReturn = "Tomando " + itemName + " de " + container.getName();
					break;

				}

			}

		}

		return (stringToReturn != null) ? stringToReturn : "No hay nada para tomar";
	}

	@Override
	public String open(String itemName) {
		String stringToReturn = null;

		for (Access a : location.getAccesses()) {
			if (a.getName().contentEquals(itemName) || a.getDescription().contentEquals(itemName)) {
				boolean possible = a.open();
				if (possible == false)
					if (a.isOpened()) {
						stringToReturn = "Ya está abierto";
						break;
					} else {
						stringToReturn = "No se pudo abrir";
						break;
					}
				else {
					stringToReturn = "Se abrió " + a.getName();
					break;
				}
			}
		}
		return (stringToReturn != null) ? stringToReturn : "No se abrió nada";

	}

	public String close() {
		return null;
	}

	@Override
	public String look(String itemName) {
		String stringToReturn = null;

		if (itemName.contentEquals("alrededor") || itemName.contentEquals(location.getName())) {

			stringToReturn = lookAround();

		} else {

			if (itemName.contentEquals("inventario")) {
				return lookInventory();
			} else {

				for (Item item : inventory) {
					if (item.getName().contentEquals(itemName)) {
						stringToReturn = item.getDescription();
						break;
					}
				}

				if (stringToReturn == null) {
					for (Access acceso : location.getAccesses()) {
						if (acceso.getName().contentEquals(itemName)
								|| acceso.getDescription().contentEquals(itemName)) {
							stringToReturn = acceso.getDescription() + ", lleva a " + acceso.getDestination().getName()
									+ ". \n" + "Está " + acceso.getStatus();
							break;
						}
					}
				}

				if (stringToReturn == null) {
					for (Item item : location.getItems()) {
						if (item.getName().contentEquals(itemName) || item.getDescription().contentEquals(itemName)) {
							stringToReturn = item.getDescription();
							break;
						}
					}
				}
			}

		}

		return (stringToReturn != null) ? stringToReturn : "No pude ver nada";
	}

	@Override
	public String unlock(String toUnlock) {
		String stringToReturn = null;
		
		for (Item item : inventory) {
			for (Access acceso : location.getAccesses())
				if (acceso.getName().contentEquals(toUnlock)) {
					acceso.recieveObject(item);
					if (!acceso.isLocked()) {
						removeItem(item);
						stringToReturn = toUnlock + " se desbloqueó";
						break;
					}
				}
			if(stringToReturn != null)
				break;
		}
		return (stringToReturn != null)? stringToReturn : "No se pudo desbloquear nada";
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
		String stringToReturn = null;
		
		for (Access access : location.getAccesses()) {
			
			if (access.getDestination().getName().contentEquals(locationName) && access.isOpened()) {
				location = access.getDestination();
				stringToReturn = "me fui a " + location.getName();
				break;
			}
			
		}
		
		return (stringToReturn != null)? stringToReturn : "No pude ir a ningun lado";
	}

	@Override
	public String grab(String itemName) {
		String stringToReturn = null;
		List<Item> items = location.getItems();
		
		for (Item i : items) {
			
			if (i.getName().contentEquals(itemName)) {
				
				inventory.add(i);
				itemName = i.getDescription();
				location.removeItem(i);
				stringToReturn = "Se agarró " + itemName;
				break;
				
			}
			
		}
		
		return (stringToReturn != null)? stringToReturn : "No se pudo agarrar nada";
	}
}
