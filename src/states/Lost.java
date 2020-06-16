package states;

import entities.Attack;
import entities.Entity;
import entities.NPC;
import island.Access;
import island.GameObject;
import island.Location;
import items.Blueprint;
import items.Item;
import items.Consumable;
import items.SingleContainer;
import items.Text;
import items.Weapon;
import tools.ItemType;
import tools.MessageType;
import tools.NPCType;

public class Lost implements State {

	private Entity character;

	public Lost(Entity character) {
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
		character.getGameManager().sendMessage(MessageType.CHARACTER, character, message);
		return result;
	}

	@Override
	public boolean look(GameObject object) {
		boolean result = false;
		character.getGameManager().sendMessage(MessageType.CHARACTER, character, object.getDescription());
		if (object.getClass() == Text.class)
			if (character.getLocation().isVisible()) {
				character.getGameManager().sendMessage(MessageType.CHARACTER, character, ((Text) object).getContent());
				result = true;
			} else {
				character.getGameManager().sendMessage(MessageType.CHARACTER, character,
						"No se puede ver nada en la oscuridad");
				result = false;
			}
		return result;
	}

	@Override
	public boolean goTo(Location location) {
		character.getGameManager().sendMessage(MessageType.CHARACTER, character, "Necesito una brujula");
		return false;
	}

	@Override
	public boolean grab(Item item) {
		boolean result = false;
		String message = "No agarre nada";
		if (item != null) {
			if (item.getClass() == Consumable.class) {
				Consumable consumable = (Consumable) item;
				if (character.getLocation().getItems().contains(consumable)) // Verificar que este bien este if para los
					if (consumable.needsContainer()) // liquidos
						for (Item i : character.getInventory()) {
							if (i.getClass() == SingleContainer.class && (((SingleContainer) i).getContent() == null)) {
								((SingleContainer) i).setContent(consumable);
								message = "Agarre " + consumable.getName();
								character.getLocation().removeItem(item);
								result = true;
								break;
							}
						}
					else {
						character.addItem(item);
						message = "Agarre " + consumable.getOnlyName();
						character.getLocation().removeItem(item);
						result = true;
					}
				else {
					message = "El objeto no esta en la misma ubicacion que la persona";
					result = false;
				}
			} else {
				if (character.getLocation().getItems().contains(item)) {
					character.addItem(item);
					character.getLocation().removeItem(item);
					message = "Agarre " + item.getOnlyName();
					result = true;
				} else {
					message = "El objeto no esta en la misma ubicacion que la persona";
					result = false;
				}
			}
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
		if (item.getClass() == SingleContainer.class) {
			SingleContainer cont = (SingleContainer) item;
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
		for (Item item : character.getLocation().getItems()) {
			message += item.getDescription() + ", ";
		}
		if (message != ", ")
			message = message.substring(0, message.length() - 2);
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
	public State heal(Double points) {
		return this;
	}

	@Override
	public State recieveAttack(Attack attack) {
		character.setHealth(
				character.getHealth() - attack.getDamage() * character.getWeaknessModifier(attack.getDamageType()));
		if (character.getHealth() <= 0) {
			character.setHealth(0d);
			character.getGameManager().sendMessage(MessageType.EVENT, character, "Cayó " + character.getSingularName());
			character.onDeath(attack);
			return new Dead(character);
		}
		return this;
	}

	@Override
	public boolean attack(Weapon weapon, Entity objective) {
		if (objective != null) {
			character.getGameManager().sendMessage(MessageType.EVENT, character, character.getName() + " Le pego a "
					+ objective.getSingularName() + " con " + weapon.getSingularName());
			Attack attack = new Attack(weapon.getDamage(), character, weapon.getDamageType());
			objective.recieveAttack(attack);
			return true;
		}
		character.getGameManager().sendMessage(MessageType.EVENT, character,
				character.getName() + " No le pude pegar a nadie");
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
		if (item.getType() == ItemType.CONSUMABLE) {
			Consumable consumable = (Consumable) item;
			consumable.consume(character);
		}
		if (item.getType() == ItemType.BLUEPRINT) {
			Blueprint bp = (Blueprint) item;
			Item result = bp.produce(character.getInventory());
			if (result != null) {
				character.addItem(result);
				return true;
			} else
				character.getGameManager().sendMessage(MessageType.EVENT, character, "No se produjo nada");
		}
		return false;
	}

	@Override
	public boolean read(Item item) {
		boolean result = true;
		if (item.getType() == ItemType.INFORMATION) {
			Text text = (Text) item;
			character.getGameManager().sendMessage(MessageType.STORY, character, text.getContent());
		} else if (item.getType() == ItemType.BLUEPRINT) {
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

}
