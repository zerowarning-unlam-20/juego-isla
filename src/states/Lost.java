package states;

import java.util.Map;

import entities.Attack;
import entities.Entity;
import entities.NPC;
import island.GameObject;
import island.Location;
import items.Access;
import items.Item;
import items.properties.Attackable;
import items.properties.Dispenser;
import items.properties.Holdable;
import items.properties.Opening;
import items.properties.Readablel;
import items.properties.Unlockable;
import items.properties.Usable;
import items.types.Bottle;
import items.types.Key;
import items.types.Liquid;
import items.types.Source;
import items.types.Weapon;
import tools.MessageType;
import tools.NPCType;

public class Lost implements State {
	private Entity character;
	private boolean completelyLost;

	public Lost(Entity character) {
		this.character = character;
		completelyLost = true;
	}

	@Override
	public boolean open(String objectName) {
		// Primero busqueda
		Access acc = null;
		if (!objectName.isEmpty()) {
			for (Access a : character.getLocation().getAccesses().values())
				if (a.getName().equalsIgnoreCase(objectName) || a.getDescription().equalsIgnoreCase(objectName)) {
					acc = a;
					break;
				}
		}
		// Procesar resultado
		boolean result = false;
		String message = "No se pudo abrir";
		if (acc == null) {
			message = "No hay nada para abrir";
		} else if (acc instanceof Access) {
			Access access = acc;
			result = access.open();
			if (!result) {
				if (access.isLocked())
					message = access.getSingularName() + " esta bloquead" + access.getTermination();
				else
					message = access.getSingularName() + "ya esta abiert" + access.getTermination();
			} else {
				message = access.getSingularName() + " se pudo abrir";
			}
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
		}
		return result;
	}

	@Override
	public boolean unlock(String toUnlockName, String keyName) {
		Access toUnlock = character.getLocation().getAccesses().get(toUnlockName);
		Item key = character.getInventory().get(keyName);

		String message = "No hay items para desbloquear ";
		boolean result = false;
		if (toUnlock != null && toUnlock instanceof Opening && toUnlock instanceof Unlockable) {
			Unlockable access = toUnlock;
			if (key instanceof Key) {
				access.unlock(key);
				result = true;
				message = toUnlock.getSingularName() + "se pudo desbloquear";
			} else {
				result = false;
				message = "Esto no sirve";
			}
		}
		character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
		return result;
	}

	@Override
	public boolean look(String name) {
		GameObject object = null;

		if (name.equalsIgnoreCase(character.getLocation().getName())) {
			object = character.getLocation();
		}
		if (object == null) {
			object = character.getInventory().get(name);
		}
		if (object == null) {
			object = character.getLocation().getArea(name);
		}
		if (object == null) {
			object = character.getLocation().getItemFromAreas(name);
		}
		if (object == null) {
			object = character.getLocation().getAccesses().get(name);
		}
		if (object == null) {
			object = character.getLocation().getEntities().get(name);
		}

		boolean result = false;
		if (object == null) {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), "No hay nada para ver");
			return false;
		}
		if (object instanceof Access
				|| object instanceof Location && (object.getName().contains("norte") || object.getName().contains("sur")
						|| object.getName().contains("este") || object.getName().contains("oeste"))) {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), "Donde es eso?");
			return false;
		}
		character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), object.getDescription());
		return result;
	}

	@Override
	public boolean goTo(String locationName) {
		if (completelyLost) {
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(),
					"Necesito algo para guiarme");
			return false;
		}
		boolean result = false;
		String message = "";
		if (locationName == null) {
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), "Ir donde?");
			return false;
		}
		Access destinationAccess = character.getLocation().getAccesses().get(locationName);
		if (locationName.equalsIgnoreCase(character.getLocation().getName())) {
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(),
					"No puedo ir donde ya estoy");
			return false;
		}
		if (destinationAccess != null) {
			if (destinationAccess.isOpened()) {
				character.getLocation().removeEntity(character);
				character.setLocation(destinationAccess.getDestination());
				character.getLocation().addEntity(character);
				message = "Me fui a" + character.getLocation().getLocationPrefix() + " "
						+ character.getLocation().getName();
				result = true;
			} else {
				message = "No se puede ir";
			}
		}
		character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
		if (result) {
			lookAround();
		}
		return result;
	}

	@Override
	public boolean grab(String name) {
		Item item = character.getLocation().getItemFromAreas(name);

		boolean result = false;
		String message = "No agarre nada";
		if (item == null || !(item instanceof Holdable)) {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), message);
			return result;
		}

		character.addItem(item);
		character.getLocation().getLastArea().removeItem(item.getName());
		character.getLocation().clearLastArea();

		message = "Se agarró " + item.getSingularName();
		result = true;
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), message);
		return result;
	}

	@Override
	public boolean grab(String sourceName, String itemName) {
		Item src = character.getLocation().getItemFromAreas(sourceName);

		String message = "No hay nada para agarrar.";
		boolean result = false;
		if (!(src instanceof Holdable)) {
			Source source = (Source) src;
			if (!(source.getContent() instanceof Liquid)) {
				result = source.giveItems(character);
			} else
				message = "Necesito un recipiente o algo para agarrarlo.";
		}
		character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
		return result;
	}

	@Override
	public boolean grab(String itemName, String sourceName, String containerName) {
		Item src = character.getLocation().getItemFromAreas(sourceName);
		Item container = character.getInventory().get(containerName);
		Item content = null;
		String message = "No hay nada para agarrar.";
		boolean result = false;
		if (!(container instanceof Bottle)) {
			message = "Esto no sirve para poner algo";
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
			return false;
		} else if (!((Bottle) container).isEmpty()) {
			message = "Este recipiente esta lleno";
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
			return false;
		}
		Bottle cont = (Bottle) container;

		if (!(src instanceof Source)) {
			message = "No puedo sacar nada de aca";
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
			return false;
		}
		Source source = (Source) src;

		if (source.getContent() instanceof Liquid) {
			content = source.getContent();
			message = "se lleno " + container.getSingularName() + " con " + content.getName();
		} else {
			message = "No necesito algo para agarrarlo.";
		}
		cont.setContent(source.getContent());
		character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
		return result;
	}

	@Override
	public boolean lookAround() {
		boolean result = true;
		String message = character.getLocation().getDescription() + "\n";
		message += character.getLocation().lookAround();
		character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
		result = true;
		return result;
	}

	@Override
	public boolean lookInventory() {
		boolean result = true;
		String message = "";

		for (Map.Entry<String, Item> itemEntry : character.getInventory().entrySet()) {
			message += itemEntry.getValue().getName() + ", ";
		}
		if (message != "")
			message = message.substring(0, message.length() - 2);
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				(message == "") ? "tiene el inventario vacio" : message);
		return result;
	}

	@Override
	public boolean attack(String weaponName, String targetName) {
		GameObject tgt = null;
		tgt = character.getLocation().getEntities().get(targetName);
		if (tgt == null) {
			tgt = character.getLocation().getItemFromAreas(targetName);
		}
		Weapon weapon = (Weapon) character.getInventory().get(weaponName);

		if (tgt == null || weapon == null) {
			if (tgt == null) {
				character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
						"No le puedo pegar a nadie");
			}
			if (weapon == null) {
				character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
						" No tengo con que pegar");
			}
			return false;
		}
		// Attack begins, checks objective
		if (tgt instanceof Attackable) {
			Attackable target = (Attackable) tgt;
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
					character.getName() + " Le pego a " + tgt.getSingularName() + " con " + weapon.getSingularName());
			Attack attack = new Attack(weapon.getDamage(), character, weapon.getDamageType());
			target.recieveAttack(attack);
			return true;
		}

		return false;
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
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
					"Cayó " + character.getSingularName());
			character.onDeath(attack);
			return new Dead(character);
		}

		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				character.getName() + ": " + character.getHealth() + " HP, Daño sufrido: " + totalDamage);

		return this;
	}

	@Override
	public boolean talk(String otherName, String message) {
		GameObject other;
		other = character.getLocation().getEntities().get(otherName);
		if (other == null) {
			other = character.getLocation().getAccesses().get(otherName);
		}
		if (other == null) {
			other = character.getInventory().get(otherName);
		}
		if (other == null) {
			other = character.getLocation().getItemFromAreas(otherName);
		}

		if (other instanceof NPC) {
			NPC npc = (NPC) other;
			if (npc.getType() == NPCType.INANIMATED) {
				character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(),
						"No puedo hablar con " + other.getOnlyName());
				return false;
			} else {
				return npc.listen(character.getName(), message);
			}
		} else {
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(),
					"No puedo hablar con " + other.getOnlyName());
		}
		return false;
	}

	@Override
	public boolean listen(String otherName, String message) {
		character.getGameManager().sendMessage(MessageType.CHARACTER, otherName, message);
		return true;
	}

	@Override
	public boolean use(String itemName) {
		Item item = character.getInventory().get(itemName);

		if (item == null) {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), "No hay nada para usar");
			return false;
		}
		if (item instanceof Usable) {
			Usable usable = (Usable) item;
			usable.use(character);
		}

		return false;
	}

	@Override
	public boolean read(String itemName) {
		Item item = character.getInventory().get(itemName);
		boolean result = true;
		String message = "Esto no se puede leer, no hay nada para leer";

		if (item == null) {
			message = "No hay nada para leer";
		}
		if (item instanceof Readablel) {
			Readablel text = (Readablel) item;
			message = text.read(character.getLocation().isVisible());
		}
		character.getGameManager().sendMessage(MessageType.STORY, character.getName(), message);
		return result;
	}

	@Override
	public boolean inspect(String itemName) {
		Item item = null;
		item = character.getInventory().get(itemName);
		if (item == null) {
			item = character.getLocation().getItemFromAreas(itemName);
		}
		if (item == null) {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), "No hay nada para revisar");
			return false;
		}
		return ((Dispenser) item).giveItems(character);
	}

	@Override
	public void lookState() {
		character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(),
				"Estoy un poco perdid" + character.getTermination());
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				"Vida: " + character.getHealth());
	}

	@Override
	public State drink(String name, String dispenserName) {
		Item dispenser = character.getInventory().get(dispenserName);
		if (dispenser == null) {
			dispenser = character.getLocation().getItemFromAreas(dispenserName);
		}

		State resultState = this;
		if (dispenser == null || !(dispenser instanceof Dispenser)) {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), "No hay nada de que tomar.");
		} else if (dispenser instanceof Bottle) {
			Bottle cont = (Bottle) dispenser;
			Item item = cont.getContent();
			if (item == null) {
				character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
						dispenser.getSingularName() + " Esta vaci" + dispenser.getTermination());
			} else {
				resultState = ((Liquid) item).consume(character);
				cont.empty();
				character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
						"tomo " + cont.getContent().getName());
			}
		} else if (dispenser instanceof Source) {
			Source src = (Source) dispenser;
			Item item = src.getContent();
			if (item == null) {
				character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
						dispenser.getSingularName() + " Esta vaci" + dispenser.getTermination());
			} else {
				resultState = ((Liquid) item).consume(character);
				character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
						"tomo " + src.getContent().getName());
			}
		}
		return resultState;
	}

	public void setCompletelyLost(boolean completelyLost) {
		this.completelyLost = completelyLost;
	}

}
