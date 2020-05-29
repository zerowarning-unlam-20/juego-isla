package states;

import entities.Entity;
import island.GameObject;
import items.Access;
import items.Item;
import items.Liquid;
import items.Location;
import items.SingleContainer;
import items.Text;
import manager.GameManager;

public class Normal implements State {
	private Entity character;

	public Normal(Entity character) {
		this.character = character;
	}

	@Override
	public boolean open(Item item) {
		boolean result = false;
		String message = "No se pudo abrir";
		if (item.getClass() == Access.class) {
			Access access = (Access) item;
			result = access.open();
			if (!result) {
				if (access.isLocked())
					message = access.getSingularName() + " esta bloquead" + access.getTermination();
				else
					message = access.getSingularName() + "ya esta abiert" + access.getTermination();
			} else {
				message = access.getSingularName() + " se pudo abrir";
			}
			GameManager.sendMessage(character, message);
		}
		return result;
	}

	@Override
	public boolean unlock(Item toUnlock) {
		String message = "No hay items para desbloquear ";
		boolean result = false;
		if (toUnlock != null && toUnlock.getClass() == Access.class) {
			Access access = (Access) toUnlock;
			if (!access.isLocked()) {
				message = access.getSingularName() + " esta bloquead" + access.getTermination();
			} else {
				message += access.getSingularName();
				for (Item item : character.getInventory()) {
					if (item.getId() == access.getIdKey()) {
						character.removeItem(item);
						access.unlock();
						message = access.getSingularName() + " se desbloqueo";
						result = true;
						break;
					}
				}
			}
		}
		GameManager.sendMessage(character, message);
		return result;
	}

	@Override
	public boolean look(GameObject object) {
		boolean result = false;
		GameManager.sendMessage(character, object.getDescription());
		if (object.getClass() == Text.class)
			if (character.getLocation().isVisible()) {
				GameManager.sendMessage(character, ((Text) object).getContent());
				result = true;
			} else {
				GameManager.sendMessage(character, "No se puede ver nada en la oscuridad");
				result = false;
			}
		return result;
	}

	@Override
	public boolean goTo(Location location) {
		boolean result = false;
		String message = "";
		for (Access access : character.getLocation().getAccesses()) {
			if (access.getDestination() == location) {
				if (access.isOpened()) {
					character.setLocation(location);
					message = "Me fui a" + location.getLocationPrefix() + " " + location.getName();
					result = true;
				} else {
					message = "No se puede ir";
				}
			}
		}
		GameManager.sendMessage(character, message);
		return result;
	}

	@Override
	public boolean grab(Item item) {
		boolean result = false;
		String message = "No agarre nada";
		if (item != null) {
			if (item.getClass() == Liquid.class) {
				for (Item i : character.getInventory()) {
					if (i.getClass() == SingleContainer.class && (((SingleContainer) i).getContent() == null)) {
						((SingleContainer) i).setContent(item);
						message = "Agarre " + item.getName();
						result = true;
						break;
					}
				}
			} else {
				character.addItem(item);
				character.getLocation().removeItem(item);
				message = "Agarre " + item.getOnlyName();
			}
		}
		GameManager.sendMessage(character, message);
		return result;
	}

	@Override
	public State drink(Item item) {
		if (item.getClass() == SingleContainer.class) {
			SingleContainer cont = (SingleContainer) item;
			cont.getContent();
			GameManager.sendMessage(character, "Tome " + cont.getName());
		} else if (item.getClass() == Liquid.class) {
			GameManager.sendMessage(character, "Tome " + item.getName());
		}
		return this;
	}

	@Override
	public boolean give(Item item, GameObject gameObject) {
		gameObject.recieveObject(item);
		return true;
	}

	@Override
	public boolean lookAround() {
		boolean result = true;
		String message = character.getLocation().getDescription() + "\n";
		for (Item item : character.getLocation().getItems()) {
			message += item + ", ";
		}
		if (message != "")
			message = message.substring(0, message.length() - 2);
		GameManager.sendMessage(character, message);
		result = true;
		return result;
	}

	@Override
	public boolean lookInventory() {
		boolean result = true;
		String message = "";
		for (Item item : character.getInventory()) {
			message += item.getName() + ", ";
		}
		if (message != "")
			message = message.substring(0, message.length() - 2);
		GameManager.sendMessage(character, (message == "") ? "Inventario vacio" : message);
		return result;
	}

	public boolean hit(Item tool, GameObject object) {
		return false;
	}

	@Override
	public State heal(Double points) {
		return this;
	}

	@Override
	public State recieveDamage(Double damage) {
		return this;
	}

}
