package states;

import entities.Attack;
import entities.Entity;
import entities.NPC;
import island.Area;
import island.GameObject;
import island.Location;
import items.Access;
import items.Item;
import items.properties.Attackable;
import items.properties.Dispenser;
import items.properties.Holdable;
import items.properties.Readablel;
import items.properties.Unlockable;
import items.properties.Usable;
import items.types.Bottle;
import items.types.Food;
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
					message = access.getNormalName() + " esta bloquead" + access.getTermination();
				else
					message = access.getNormalName() + "ya esta abiert" + access.getTermination();
			} else {
				message = access.getNormalName() + " se pudo abrir";
			}
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
		}
		return result;
	}

	@Override
	public boolean unlock(String toUnlockName, String keyName) {
		Access toUnlock = character.getLocation().getAccesses().get(toUnlockName);
		if (toUnlock == null) {
			toUnlock = character.getLocation().getAccessForUse(toUnlockName);
		}
		Item key = character.getInventory().getItem(keyName);

		String message = "No hay items para desbloquear";
		boolean result = false;
		if (toUnlock instanceof Unlockable) {
			Unlockable access = toUnlock;
			if (key instanceof Key) {
				access.unlock(key);
				result = true;
				message = toUnlock.getNormalName() + "se pudo desbloquear";
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
			object = character.getInventory().getItem(name);
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
		String message = "";
		if (object == null) {
			message = "No hay nada para ver";
		} else if (object instanceof Area) {
			Area area = (Area) object;
			message = area.getContentString();
		} else if (object instanceof Access
				|| object instanceof Location && (object.getName().contains("norte") || object.getName().contains("sur")
						|| object.getName().contains("este") || object.getName().contains("oeste"))) {
			message = "Donde es eso?";
		} else if (object instanceof Entity) {
			Entity entity = (Entity) object;
			message = entity.getDescription();
			message += (entity.getState().getClass() == Dead.class) ? ", esta muert" + entity.getTermination() : "";
		} else if (object instanceof Bottle) {
			message = object.getDescription();
			message += ((Bottle) object).getContent() == null ? " vacia"
					: " llena de " + ((Bottle) object).getContent().getDescription();
		} else {
			message = object.getDescription();
		}
		character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
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
		Access destinationAccess = character.getLocation().getAccesses().get(locationName);

		if (locationName.equalsIgnoreCase(character.getLocation().getName())) {
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(),
					"No puedo ir donde ya estoy");
			return false;
		}

		if (destinationAccess == null) {
			message = "Ir donde?";
		} else {
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
		character.getLocation().getLastArea().removeItem(item.getName().toLowerCase());
		character.getLocation().clearLastArea();

		message = "Se agarro " + item.getNormalName();
		result = true;
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), message);
		return result;
	}

	@Override
	public boolean grab(String sourceName, String itemName) {
		Item src = character.getLocation().getItemFromAreas(sourceName);

		String message = "No hay nada para agarrar.";
		boolean result = false;
		if (src != null && !(src instanceof Holdable)) {
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
		Item container = character.getInventory().getItem(containerName);
		Item content = null;
		String message = "No hay nada para agarrar.";
		boolean result = false;
		if (container != null && !(container instanceof Bottle)) {
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
			message = "se lleno " + container.getNormalName() + " con " + content.getName();
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
		message = character.getInventory().showItems();
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), message);
		return result;
	}

	@Override
	public boolean attack(String weaponName, String targetName) {
		GameObject tgt = null;
		tgt = character.getLocation().getEntities().get(targetName);
		if (tgt == null) {
			tgt = character.getLocation().getItemFromAreas(targetName);
		}
		Item item = character.getInventory().getItem(weaponName);

		if (tgt == null || item == null) {
			if (tgt == null) {
				character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
						"No le puedo pegar a nadie");
			}
			if (item == null) {
				character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
						" No tengo con que pegar");
			}
			return false;
		}
		if (!(item instanceof Weapon)) {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
					"No puedo pegar con " + item.getNormalName() + ", no es un arma.");
			return false;
		}
		Weapon weapon = (Weapon) item;

		// Attack begins, checks objective
		if (tgt instanceof Attackable) {
			Attackable target = (Attackable) tgt;
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
					character.getName() + " Le pego a " + tgt.getNormalName() + " con " + weapon.getNormalName());
			Attack attack = new Attack(weapon.getDamage(), character, weapon.getDamageType());
			target.recieveAttack(attack);
			return true;
		} else {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
					"No puedo golpear a " + tgt.getNormalName());
		}

		return false;
	}

	@Override
	public State receiveAttack(Attack attack) {
		Double modifier = character.getWeaknessModifier(attack.getDamageType());
		Double totalDamage = 0d;
		if (modifier != null)
			totalDamage = modifier * attack.getDamage();
		else
			totalDamage = attack.getDamage();
		if (character.getHealth() - totalDamage < 0)
			character.setHealth(0d);
		else
			character.setHealth(character.getHealth() - totalDamage);

		if (character.getHealth() <= 0) {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
					"Cayo " + character.getNormalName());
			character.onDeath(attack);
			return new Dead(character);
		}

		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
				character.getName() + ": " + character.getHealth() + " HP, Dano sufrido: " + totalDamage);

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
			other = character.getInventory().getItem(otherName);
		}
		if (other == null) {
			other = character.getLocation().getItemFromAreas(otherName);
		}
		if (other == null) {
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), "¿Eh?");
			return false;
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
		Item item = character.getInventory().getItem(itemName);

		if (item == null) {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), "No hay nada para usar");
			return false;
		} else if (item instanceof Usable) {
			Usable usable = (Usable) item;
			usable.use(character);
		} else
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
					"No le encuentro ningun uso");

		return false;
	}

	@Override
	public boolean read(String itemName) {
		Item item = character.getInventory().getItem(itemName);
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
		boolean result = false;
		Item item = null;
		item = character.getInventory().getItem(itemName);
		if (item == null) {
			item = character.getLocation().getItemFromAreas(itemName);
		}
		if (item == null) {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), "No hay nada para revisar");
		} else if (item instanceof Readablel) {
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(),
					((Readablel) item).read(character.getLocation().isVisible()));
			return true;
		} else if (item instanceof Dispenser) {
			result = ((Dispenser) item).giveItems(character);
		} else
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), item.getDescription());
		return result;
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
		Item dispenser = character.getInventory().getItem(dispenserName);
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
						dispenser.getNormalName() + " Esta vaci" + dispenser.getTermination());
			} else {
				resultState = ((Liquid) item).consume(character);
				character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
						"tomo " + item.getName());
				cont.empty();
			}
		} else if (dispenser instanceof Source) {
			Source src = (Source) dispenser;
			Item item = src.getContent();
			if (item == null) {
				character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
						dispenser.getNormalName() + " Esta vaci" + dispenser.getTermination());
			} else {
				resultState = ((Liquid) item).consume(character);
				character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
						"tomo " + src.getContent().getName());
			}
		}
		return resultState;
	}

	@Override
	public boolean drop(String item) {
		String message = "";
		boolean result = false;
		Item resultItem = character.getInventory().getItem(item);
		if (resultItem != null) {
			message += "tirando " + resultItem.getName();
			result = character.getLocation().addItem(resultItem);
			if (result)
				character.getInventory().removeItem(resultItem.getName());

		} else
			message += "no hay nada para tirar";
		if (!result && !character.getLocation().isDropZone()) {
			message += "no se puede tirar nada aca";
		}
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), message);
		return result;
	}

	public void setCompletelyLost(boolean completelyLost) {
		this.completelyLost = completelyLost;
	}

	public Object getCompletelyLost() {
		return completelyLost;
	}

	@Override
	public State eat(String name) {
		Item item = character.getInventory().getItem(name);
		if (item instanceof Food) {
			Food food = (Food) item;
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), "comi " + food.getName());
			return food.consume(character);
		}
		character.getGameManager().sendMessage(MessageType.EVENT, character.getName(), "no comi nada");

		return this;
	}

}
