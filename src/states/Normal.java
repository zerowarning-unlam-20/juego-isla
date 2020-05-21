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
	public void open(Item item) {
		String message = "No se pudo abrir";
		if (item.getClass() == Access.class) {
			Access access = (Access) item;
			boolean result = access.open();
			if (!result) {
				if (access.isLocked())
					message = access.getSingularName() + " esta bloquead" + access.getTermination();
				else
					message = access.getSingularName() + "ya esta abiert" + access.getTermination();
			} else
				message = access.getSingularName() + " se pudo abrir";
			GameManager.getInstance().recieveMessage(character.getName(), message);
		}
	}

	@Override
	public void unlock(Item toUnlock) {
		String message = "No hay items para desbloquear ";
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
						break;
					}
				}
			}
		}
		GameManager.getInstance().recieveMessage(character.getName(), message);
	}

	@Override
	public void look(GameObject object) {
		GameManager.getInstance().recieveMessage(character.getName(), object.getDescription());
		if (object.getClass() == Text.class)
			if (character.getLocation().isVisible())
				GameManager.getInstance().recieveMessage(character.getName(), ((Text) object).getContent());
			else
				GameManager.getInstance().recieveMessage(character.getName(), "No se puede ver nada en la oscuridad");
	}

	@Override
	public void goTo(Location location) {
		String message = "";
		for (Access access : character.getLocation().getAccesses()) {
			if (access.getDestination() == location) {
				if (access.isOpened()) {
					character.setLocation(location);
					message = "Me fui a" + location.getLocationPrefix() + " " + location.getName();
				} else {
				}
			}
		}
		GameManager.getInstance().recieveMessage(character.getName(), message);
	}

	@Override
	public void grab(Item item) {
		String message = "No agarre nada";
		if (item != null) {
			if (item.getClass() == Liquid.class) {
				for (Item i : character.getInventory()) {
					if (i.getClass() == SingleContainer.class && (((SingleContainer) i).getContent() == null)) {
						((SingleContainer) i).setContent(item);
						message = "Agarre " + item.getName();
						break;
					}
				}
			} else {
				character.addItem(item);
				character.getLocation().removeItem(item);
				message = "Agarre " + item.getOnlyName();
			}
		}
		GameManager.getInstance().recieveMessage(character.getName(), message);
	}

	@Override
	public State drink(Item item) {
		if (item.getClass() == SingleContainer.class) {
			SingleContainer cont = (SingleContainer) item;
			cont.getContent();
			GameManager.getInstance().recieveMessage(character.getName(), "Tome " + cont.getName());
		} else if (item.getClass() == Liquid.class) {
			GameManager.getInstance().recieveMessage(character.getName(), "Tome " + item.getName());
		}
		return this;
	}

	@Override
	public void give(Item item, GameObject gameObject) {
		gameObject.recieveObject(item);
	}

	@Override
	public void lookAround() {
		String message = character.getLocation().getDescription() + "\n";
		for (Item item : character.getLocation().getItems()) {
			message += item + ", ";
		}
		if (message != "")
			message = message.substring(0, message.length() - 2);
		GameManager.getInstance().recieveMessage(character.getName(), message);
	}

	@Override
	public void lookInventory() {
		String message = "";
		for (Item item : character.getInventory()) {
			message += item.getName() + ", ";
		}
		if (message != "")
			message = message.substring(0, message.length() - 2);
		GameManager.getInstance().recieveMessage(character.getName(), (message == "") ? "Inventario vacio" : message);
	}

	public void hit(Item tool, GameObject object) {

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
