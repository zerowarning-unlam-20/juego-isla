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
import items.types.Consumable;
import items.types.Key;
import items.types.Container;
import items.types.Weapon;
import tools.MessageType;

public class NPCNormal implements State {
	private NPC character;

	public NPCNormal(NPC character) {
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
		return false;
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

		if (item != null) {
			if (item instanceof Holdable) {
				character.addItem(item);
				character.getLocation().removeItem(item);
			}
			if (item.getClass().equals(Consumable.class) && ((Consumable) item).needsContainer()) {
				for (Item container : character.getInventory()) {
					if (container.getClass().equals(Container.class) && ((Container) container).isEmpty()) {
						((Container) container).setContent(item);
						message = "Se ingreso" + item.getSingularName() + " en " + container.getSingularName();
						character.getLocation().removeItem(item);
					}
				}
			}
		}

		character.getGameManager().sendMessage(MessageType.EVENT, character, message);
		return result;
	}

	@Override
	public State drink(Item item) {
		if (item.getClass() == Container.class) {
			Container cont = (Container) item;
			cont.getContent();
			character.getGameManager().sendMessage(MessageType.EVENT, character, "tomo " + cont.getName());
		} else if (item.getClass() == Consumable.class) {
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
		Double totalDamage = 0d;
		if (modifier != null)
			totalDamage = modifier * attack.getDamage();
		else
			totalDamage = attack.getDamage();

		character.setHealth(character.getHealth() - totalDamage);

		if (character.getHealth() <= 0) {
			character.setHealth(0d);
			character.getGameManager().sendMessage(MessageType.EVENT, character, "Cayó " + character.getSingularName());
			character.onDeath(attack);
			return new Dead(character);
		}

		character.getGameManager().sendMessage(MessageType.EVENT, character,
				character.getName() + ": " + character.getHealth() + " HP, Daño sufrido: " + totalDamage);

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
		if (objective instanceof Entity) {
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
		return other.listen(character, message);
	}

	@Override
	public boolean listen(Entity other, String message) {
		String answer = character.getChat(message);
		talk(other, (answer != null) ? answer : character.getChat("デフォールト"));
		return answer != null;
	}

	@Override
	public boolean use(Item item) {
		if (item == null) {
			character.getGameManager().sendMessage(MessageType.EVENT, character, "No hay nada para usar");
			return false;
		}
		if (item instanceof Usable) {
			Usable usable = (Usable) item;
			usable.use(character);
		}
		return false;
	}
	
	@Override
	public boolean read(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Item item) {
		// TODO Auto-generated method stub
		return false;
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
