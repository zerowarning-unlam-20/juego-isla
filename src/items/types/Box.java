package items.types;

import java.util.List;

import entities.Attack;
import entities.Entity;
import items.Item;
import items.properties.Attackable;
import items.properties.Dispenser;
import items.properties.Unlockable;
import tools.DamageType;
import tools.Gender;
import tools.MessageType;

public class Box extends Item implements Unlockable, Dispenser, Attackable {
	private boolean locked;
	private String idKey;
	private List<Item> content;
	private DamageType weakness;

	public Box(Gender gender, String name, String description, boolean locked, String idKey, List<Item> items) {
		super(gender, name, description);
		this.locked = locked;
		this.content = items;
	}

	@Override
	public void unlock() {
		locked = false;
	}

	@Override
	public String inspect() {
		if (!locked) {
			String message = "Contenido de " + this.getSingularName() + "\n";
			String contentMessage = "";
			if (contentMessage.isEmpty()) {
				return this.getSingularName() + "esta vaci" + this.getTermination();
			}
			for (Item item : content) {
				contentMessage += item.getName() + ", ";
			}
			if (contentMessage != "") {
				contentMessage = contentMessage.substring(0, contentMessage.length() - 2);
				contentMessage += ".";
			}
			return message + contentMessage;
		}
		return ("No se puede ver lo que est� dentro de " + this.getSingularName());
	}

	@Override
	public boolean unlock(Item keyItem) {
		boolean result = false;
		if (locked) {
			if (keyItem.getName().equalsIgnoreCase(idKey)) {
				locked = false;
			}
			result = true;
		}
		return result;
	}

	@Override
	public boolean giveItems(Entity entity) {
		boolean result = false;
		if (!locked) {
			result = !content.isEmpty();
			if (content.isEmpty()) {
				entity.getGameManager().sendMessage(MessageType.EVENT, entity.getName(),
						"Esta vaci" + this.getTermination());
			}
			for (Item item : content) {
				entity.addItem(item);
			}
			content.clear();
		}
		if (locked)
			entity.getGameManager().sendMessage(MessageType.EVENT, entity.getName(), "No se puede inspeccionar");
		return result;
	}

	@Override
	public boolean recieveAttack(Attack attack) {
		boolean result = false;
		if (this.weakness != null) {
			if (attack.getDamageType().equals(this.weakness)) {
				this.unlock();
				result = true;
			}
		}
		return result;
	}
}