package states;

import entities.Attack;
import entities.Entity;
import entities.NPC;
import island.GameObject;
import island.Location;
import items.Item;
import items.Access;
import items.properties.Attackable;
import items.properties.Dispenser;
import items.properties.Holdable;
import items.properties.Usable;
import items.types.Blueprint;
import items.types.Consumable;
import items.properties.Readablel;
import items.types.Key;
import items.types.Container;
import items.types.Text;
import items.types.Weapon;
import tools.MessageType;
import tools.NPCType;

public class Normal implements State {
	private Entity character;

	public Normal(Entity character) {
		this.character = character;
	}

	@Override
	public boolean open(GameObject object) {
		boolean result = false;
		String message = "No se pudo abrir";
		if (object == null) {
			message = "No hay nada para abrir";
		} else if (object.getClass() == Access.class) {
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
			character.getGameManager().sendMessage(MessageType.CHARACTER, character, message);
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
					if (item instanceof Key && access.unlock(item)) {
						character.removeItem(item);
						message = access.getSingularName() + " se desbloqueo";
						result = true;
						break;
					}
				}
			}
		}
		character.getGameManager().sendMessage(MessageType.CHARACTER, character, message);
		return result;
	}

	@Override
	public boolean look(GameObject object) {
		boolean result = false;
		if (object == null) {
			character.getGameManager().sendMessage(MessageType.CHARACTER, character, "No hay nada que ver");
			return result;
		}
		character.getGameManager().sendMessage(MessageType.CHARACTER, character, object.getDescription());
		if (object.getClass() == NPC.class) {
			NPC npc = (NPC) object;
			if (npc.getState().getClass().equals(Dead.class)) {
				Attack attack = new Attack(0d, character, null);
				npc.onDeath(attack);
			}
		}
		return result;
	}

	@Override
	public boolean goTo(Location location) {
		boolean result = false;
		String message = "";
		if (location == null) {
			character.getGameManager().sendMessage(MessageType.CHARACTER, character, "Ir donde?");
			return false;
		}
		Access destinationAccess = character.getLocation().getAccesses().get(location.getId());
		if (destinationAccess != null) {
			if (destinationAccess.isOpened()) {
				character.getLocation().removeEntity(character);
				character.setLocation(location);
				character.getLocation().addEntity(character);
				message = "Me fui a" + location.getLocationPrefix() + " " + location.getName();
				result = true;
			} else {
				message = "No se puede ir";
			}
		}
		character.getGameManager().sendMessage(MessageType.CHARACTER, character, message);
		if (result) {
			lookAround();
		}
		return result;
	}

	@Override
	public boolean grab(Item item) {
		boolean result = false;
		String message = "No agarre nada";

		if (item == null) {
			character.getGameManager().sendMessage(MessageType.EVENT, character, message);
			return result;
		} else if (!(item instanceof Holdable)) {
			message = "Imposible agarrar " + item.getSingularName();
			character.getGameManager().sendMessage(MessageType.EVENT, character, message);
			return result;
		}

		if (item instanceof Consumable && ((Consumable) item).needsContainer()) {
			for (Item container : character.getInventory()) {
				if (container instanceof Container && ((Container) container).isEmpty()) {
					((Container) container).setContent(item);
					message = "Se ingreso " + item.getSingularName().toLowerCase() + " en "
							+ container.getSingularName();
					character.getLocation().removeItem(item);
					result = true;
				}
			}
		} else {
			character.addItem(item);
			character.getLocation().removeItem(item);
			message = "Se agarró " + item.getSingularName();
			result = true;
		}
		character.getGameManager().sendMessage(MessageType.EVENT, character, message);
		return result;
	}

	@Override
	public State drink(Item item) {
		if (item == null) {
			character.getGameManager().sendMessage(MessageType.EVENT, character, "No hay nada que tomar");
			return this;
		}
		if (item.getClass() == Container.class) {
			Container cont = (Container) item;
			cont.getContent();
			character.getGameManager().sendMessage(MessageType.EVENT, character, "tomo " + cont.getName());
		} else if (item instanceof Consumable) {
			character.getGameManager().sendMessage(MessageType.EVENT, character, "Tome " + item.getName());
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
		message += character.getLocation().lookAround();
		character.getGameManager().sendMessage(MessageType.CHARACTER, character, message);
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
		character.getGameManager().sendMessage(MessageType.EVENT, character,
				(message == "") ? "tiene el inventario vacio" : message);
		return result;
	}

	public boolean hit(Item tool, GameObject object) {
		return false;
	}

	@Override
	public void heal(Double points) {
		if (!character.getState().getClass().equals(Dead.class)) {
			Double total = character.getHealth() + points;
			character.setHealth(total > character.getBaseHealth() ? total : character.getBaseHealth());
		}
	}

	@Override
	public State recieveAttack(Attack attack) {
		Double modifier = character.getWeaknessModifier(attack.getDamageType());
		if (modifier != null)
			character.setHealth(character.getHealth() - attack.getDamage() * modifier);
		else
			character.setHealth(character.getHealth() - attack.getDamage());
		if (character.getHealth() <= 0) {
			character.setHealth(0d);
			character.getGameManager().sendMessage(MessageType.EVENT, character, "Cayó " + character.getSingularName());
			character.onDeath(attack);
			return new Dead(character);
		}
		return this;
	}

	@Override
	public boolean attack(Weapon weapon, GameObject objective) {
		if (objective == null || weapon == null) {
			if (objective == null) {
				character.getGameManager().sendMessage(MessageType.EVENT, character, "No le puedo pegar a nadie");
			}
			if (weapon == null) {
				character.getGameManager().sendMessage(MessageType.EVENT, character, " No tengo con que pegar");
			}
			return false;
		}

		// Attack begins, checks objective
		if (objective instanceof Attackable) {
			Attackable target = (Attackable) objective;
			character.getGameManager().sendMessage(MessageType.EVENT, character, character.getName() + " Le pego a "
					+ objective.getSingularName() + " con " + weapon.getSingularName());
			Attack attack = new Attack(weapon.getDamage(), character, weapon.getDamageType());
			target.recieveAttack(attack);
			return true;
		}
		return false;
	}

	@Override
	public boolean talk(Entity other, String message) {
		if (other.getClass() == NPC.class && ((NPC) other).getType() == NPCType.INANIMATED) {
			character.getGameManager().sendMessage(MessageType.CHARACTER, character,
					"No puedo hablar con " + other.getOnlyName());
			return false;
		}
		return other.listen(character, message);
	}

	@Override
	public boolean listen(Entity other, String message) {
		character.getGameManager().sendMessage(MessageType.CHARACTER, other, message);
		return true;
	}

	@Override
	public boolean use(Item item) {
		if (item == null) {
			character.getGameManager().sendMessage(MessageType.EVENT, character, "No hay nada para usar");
			return false;
		}
		if (item instanceof Usable) {
			Usable usable = (Usable) item;
			return usable.use(character);
		}
		return false;
	}

	@Override
	public boolean read(Item item) {
		boolean result = true;
		if (item instanceof Readablel) {
			Readablel text = (Readablel) item;
			character.getGameManager().sendMessage(MessageType.STORY, character,
					text.read(character.getLocation().isVisible()));
		} else if (item instanceof Blueprint) {
			Blueprint bp = (Blueprint) item;
			character.getGameManager().sendMessage(MessageType.STORY, character, bp.getDescription());
		}
		return result;
	}

	@Override
	public boolean create(Item item) {
		boolean result = false;
		if (item != null)
			character.getGameManager().sendMessage(MessageType.EVENT, character, "No hay nada para crear");

		return result;
	}

	@Override
	public void lookState() {
		character.getGameManager().sendMessage(MessageType.CHARACTER, character,
				"Estoy un poco perdid" + character.getTermination());
		character.getGameManager().sendMessage(MessageType.EVENT, character, "Vida: " + character.getHealth());
	}

	@Override
	public boolean inspect(Item item) {
		if (item == null) {
			character.getGameManager().sendMessage(MessageType.EVENT, character, "No hay nada para revisar");
			return false;
		}
		return ((Dispenser) item).giveItems(character);
	}

}
