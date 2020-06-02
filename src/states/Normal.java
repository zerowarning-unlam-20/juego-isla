package states;

import entities.Attack;
import entities.Entity;
import island.GameObject;
import items.Access;
import items.Item;
import items.Liquid;
import items.Location;
import items.SingleContainer;
import items.Text;
import items.Weapon;
import manager.GameManager;
import tools.MessageType;

public class Normal implements State {
	private Entity character;

	public Normal(Entity character) {
		this.character = character;
	}

	@Override
	public boolean open(GameObject object) {
		boolean result = false;
		String message = "No se pudo abrir";
		if (object.getClass() == Access.class) {
			Access access = (Access) object;
			result = access.open();
			if (!result) {
				if (access.isLocked())
					message = access.getSingularName() + " esta bloquead" + access.getTermination();
				else
					message = access.getSingularName() + "ya esta abiert" + access.getTermination();
			} else {
				message = access.getSingularName() + " se pudo abrir";
			}
			GameManager.sendMessage(MessageType.CHARACTER, character, message);
		}
		return result;
	}

	@Override
	public boolean unlock(GameObject toUnlock) {
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
		GameManager.sendMessage(MessageType.CHARACTER, character, message);
		return result;
	}

	@Override
	public boolean look(GameObject object) {
		boolean result = false;
		GameManager.sendMessage(MessageType.CHARACTER, character, object.getDescription());
		if (object.getClass() == Text.class)
			if (character.getLocation().isVisible()) {
				GameManager.sendMessage(MessageType.CHARACTER, character, ((Text) object).getContent());
				result = true;
			} else {
				GameManager.sendMessage(MessageType.CHARACTER, character, "No se puede ver nada en la oscuridad");
				result = false;
			}
		return result;
	}

	@Override
	public boolean goTo(Location location) {
		boolean result = false;
		String message = "";
		for (Access access : character.getLocation().getAccesses().values()) {
			if (access.getDestination() == location) {
				if (access.isOpened()) {
					character.getLocation().removeEntity(character);
					character.setLocation(location);
					character.getLocation().addEntity(character);
					message = "Me fui a" + location.getLocationPrefix() + " " + location.getName();
					result = true;
				} else {
					message = "No se puede ir";
				}
			}
		}
		GameManager.sendMessage(MessageType.CHARACTER, character, message);
		return result;
	}

	@Override
	public boolean grab(Item item) {
		boolean result = false;
		String message = "No agarro nada";
		if (item != null) {
			if (item.getClass() == Liquid.class) {
				for (Item i : character.getInventory()) {
					if (i.getClass() == SingleContainer.class && (((SingleContainer) i).getContent() == null)) {
						((SingleContainer) i).setContent(item);
						message = "agarro " + item.getName();
						result = true;
						break;
					}
				}
			} else {
				character.addItem(item);
				character.getLocation().removeItem(item);
				message = "agarro " + item.getOnlyName();
			}
		}
		GameManager.sendMessage(MessageType.EVENT, character, message);
		return result;
	}

	@Override
	public State drink(Item item) {
		if (item.getClass() == SingleContainer.class) {
			SingleContainer cont = (SingleContainer) item;
			cont.getContent();
			GameManager.sendMessage(MessageType.EVENT, character, "tomo " + cont.getName());
		} else if (item.getClass() == Liquid.class) {
			GameManager.sendMessage(MessageType.EVENT, character, "Tome " + item.getName());
		}
		return this;
	}

	@Override
	public boolean give(Item item, GameObject gameObject) {
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
		GameManager.sendMessage(MessageType.CHARACTER, character, message);
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
		GameManager.sendMessage(MessageType.EVENT, character, (message == "") ? "tiene el inventario vacio" : message);
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
	public State recieveAttack(Attack attack) {
		character.setHealth(
				character.getHealth() - attack.getDamage() * character.getWeaknessModifier(attack.getDamageType()));
		if (character.getHealth() <= 0) {
			character.setHealth(0d);
			GameManager.sendMessage(MessageType.EVENT, character, "Cayó " + character.getSingularName());
			character.onDeath(attack);
			return new Dead(character);
		}
		return this;
	}

	@Override
	public boolean attack(Weapon weapon, Entity objective) {
		GameManager.sendMessage(MessageType.EVENT, character,
				character.getName() + " Le pego a " + objective.getSingularName() + " con " + weapon.getSingularName());
		Attack attack = new Attack(weapon.getDamage(), character, weapon.getDamageType());
		objective.recieveAttack(attack);
		return true;
	}

}
