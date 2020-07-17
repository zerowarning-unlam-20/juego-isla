package items.types;

import java.util.List;

import entities.Attack;
import entities.Entity;
import items.Item;
import items.properties.Attackable;
import items.properties.Dispenser;
import tools.DamageType;
import tools.Gender;
import tools.MessageType;
import tools.Namber;

public class Box extends Item implements Dispenser, Attackable {
	protected boolean locked;
	protected List<Item> content;
	protected DamageType weakness;

	public Box(Gender gender, Namber number, String name, String description, int price, boolean locked, String idKey,
			List<Item> items) {
		super(gender, number, name, description, price);
		this.locked = locked;
		this.content = items;
	}

	@Override
	public String inspect() {
		if (!locked) {
			String message = "Contenido de " + this.getNormalName() + "\n";
			String contentMessage = "";
			if (contentMessage.isEmpty()) {
				return this.getNormalName() + "esta vaci" + this.getTermination();
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
		return ("No se puede ver lo que está dentro de " + this.getNormalName() + ", esta bloquead"
				+ this.getTermination());
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
				this.locked = false;
				result = true;
			}
		}
		return result;
	}
}
