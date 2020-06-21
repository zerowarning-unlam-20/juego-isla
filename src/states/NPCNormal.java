package states;

import entities.Attack;
import entities.Entity;
import entities.NPC;
import island.GameObject;
import island.Location;
import items.Access;
import items.Item;
import items.properties.Attackable;
import items.properties.Dispenser;
import items.properties.Usable;
import items.types.Key;
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
		if (object == null) {
			message = "No hay nada para abrir";
		} else if (object instanceof Access) {
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
	public boolean unlock(GameObject toUnlock, Item key) {
		String message = "No hay items para desbloquear ";
		boolean result = false;
		if (toUnlock != null && toUnlock instanceof Access) {
			Access access = (Access) toUnlock;
			if (key instanceof Key) {
				access.unlock(key);
				result = true;
				message = access.getSingularName() + "se pudo desbloquear";
			} else {
				result = false;
				message = "Esto no sirve";
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
		return false;
	}

	@Override
	public boolean grab(Item item, Item content) {
		return false;
	}

	@Override
	public State drink(Item item) {
		return this;
	}

	@Override
	public boolean give(Item item, GameObject gameObject) {
		return false;
	}

	@Override
	public boolean lookAround() {
		return false;
	}

	@Override
	public boolean lookInventory() {
		return false;
	}

	@Override
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
