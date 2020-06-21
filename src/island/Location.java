package island;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Entity;
import entities.NPC;
import entities.Player;
import items.Access;
import items.Item;
import tools.Gender;

public class Location extends GameObject {
	private boolean visible;

	private HashMap<String, Item> items;

	private HashMap<String, Access> accesses;

	private HashMap<String, Entity> entities;

	public Location(Gender gender, String name, String description, boolean visible, HashMap<String, Item> items,
			HashMap<String, Access> accesses) {
		super(gender, name, description);
		this.items = items;
		this.visible = visible;
		this.entities = new HashMap<String, Entity>();
		this.accesses = accesses;
	}

	public String getDescription() {
		return description;
	}

	public String lookAround() {
		String acc = "";
		String ent = "";
		String message = "";

		for (Access access : accesses.values()) {
			acc += access.getDescription() + ", ";
		}
		if (acc.contains(", ")) {
			acc = acc.substring(0, acc.length() - 2);
			acc += ".\n";
		}
		if (!acc.isEmpty()) {
			message += "Se ve: " + acc;
		}
		for (Entity entity : entities.values()) {
			if (!entity.getClass().equals(Player.class))
				ent += entity.getDescription() + ", ";
		}
		if (ent.contains(", ")) {
			ent = ent.substring(0, ent.length() - 2);
			ent += ".\n";
		}
		message += ent;
		for (Item item : items.values()) {
			message += item.getDescription() + ", ";
		}
		if (message.length() > 2 && message.substring(message.length() - 2, message.length()).contains(", "))
			message = message.substring(0, message.length() - 2);

		return message;
	}

	@Override
	public String toString() {
		return "[" + name + "]";
	}

	public HashMap<String, Item> getItems() {
		return items;
	}

	public HashMap<String, Access> getAccesses() {
		return accesses;
	}

	public void addAccess(Access access) {
		accesses.put(access.getDestinationName().toLowerCase(), access);
	}

	public boolean addLink(Location other) {
		Access access = accesses.get(other.name);
		if (access != null) {
			access.setDestination(other);
			Access b = access.getDestination().getAccesses().get(this.name);
			if (b != null)
				b.setDestination(this);
			return true;
		}
		return false;
	}

	public void removeItem(Item i) {
		items.remove(i.name);
	}

	public HashMap<String, Entity> getEntities() {
		return entities;
	}

	public void addNpc(NPC npc) {
		entities.put(npc.name, npc);
	}

	public void removeNpc(NPC npc) {
		entities.remove(npc.name);
	}

	public boolean isVisible() {
		return visible;
	}

	public void addItem(Item item) {
		items.put(item.name, item);
	}

	public void addEntity(Entity entity) {
		entities.put(entity.name, entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity.name);
	}
}
