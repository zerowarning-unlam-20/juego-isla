package island;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Entity;
import entities.NPC;
import entities.UserCharacter;
import items.Access;
import items.Item;
import tools.Gender;

public class Location extends GameObject {
	private boolean visible;

	private List<Item> items;

	private HashMap<Integer, Access> accesses;

	private HashMap<Integer, Entity> entities;

	public Location(int id, Gender gender, String name, String description, boolean visible, List<Item> items,
			HashMap<Integer, Access> accesses) {
		super(id, gender, name, description);
		this.items = items;
		this.visible = visible;
		this.entities = new HashMap<Integer, Entity>();
		this.accesses = accesses;
	}

	public Location(int id, Gender gender, String name, String description, boolean visible, List<Item> items,
			List<Access> accesses) {
		super(id, gender, name, description);
		this.items = items;
		this.visible = visible;
		this.accesses = new HashMap<Integer, Access>();
		this.entities = new HashMap<Integer, Entity>();
		for (Access a : accesses) {
			this.accesses.put(a.getIdDestination(), a);
		}
	}

	public Location(int id, Gender gender, String name, String description, boolean visible, List<Item> items,
			HashMap<Integer, Access> accesses, HashMap<Integer, Entity> entities) {
		super(id, gender, name, description);
		this.items = items;
		this.visible = visible;
		this.entities = entities;
		this.accesses = accesses;
	}

	public Location(int id, Gender gender, String name, String description, boolean visible, List<Item> items) {
		super(id, gender, name, description);
		this.items = new ArrayList<Item>();
		this.accesses = new HashMap<Integer, Access>();
		this.entities = new HashMap<Integer, Entity>();
		this.items = items;
		this.visible = visible;
	}

	public Location(int id, Gender gender, String name, String description, boolean visible) {
		super(id, gender, name, description);
		this.items = new ArrayList<Item>();
		this.accesses = new HashMap<Integer, Access>();
		this.entities = new HashMap<Integer, Entity>();
		this.visible = visible;
	}

	public String getDescription() {
		return description;
	}

	public String lookAround() {
		String acc = "";
		String ent = "";
		String message = "";

		for (Map.Entry<Integer, Access> entry : accesses.entrySet()) {
			acc += entry.getValue().getDescription() + ", ";
		}
		if (acc.contains(", ")) {
			acc = acc.substring(0, acc.length() - 2);
			acc += ".\n";
		}
		if (!acc.isEmpty()) {
			message += "Se ve: " + acc;
		}
		for (Map.Entry<Integer, Entity> entry : entities.entrySet()) {
			if (!entry.getValue().getClass().equals(UserCharacter.class))
				ent += entry.getValue().getDescription() + ", ";
		}
		if (ent.contains(", ")) {
			ent = ent.substring(0, ent.length() - 2);
			ent += ".\n";
		}
		message += ent;
		for (Item item : items) {
			message += item.getDescription() + ", ";
		}
		if (message.contains(", "))
			message = message.substring(0, message.length() - 2);

		return message;
	}

	@Override
	public String toString() {
		return "[" + name + "]";
	}

	public List<Item> getItems() {
		return items;
	}

	public HashMap<Integer, Access> getAccesses() {
		return accesses;
	}

	public void setAccesses(HashMap<Integer, Access> accesses) {
		this.accesses = accesses;
	}

	public void addAccess(Access access) {
		accesses.put(access.getIdDestination(), access);
	}

	public boolean addLink(Location other) {
		Access access = accesses.get(other.id);
		if (access != null) {
			access.setDestination(other);
			Access b = access.getDestination().getAccesses().get(this.id);
			if (b != null)
				b.setDestination(this);
			return true;
		}
		return false;
	}

	public void removeItem(Item i) {
		items.remove(i);
	}

	public HashMap<Integer, Entity> getEntities() {
		return entities;
	}

	public void addNpc(NPC npc) {
		entities.put(npc.getId(), npc);
	}

	public void removeNpc(NPC npc) {
		entities.remove(npc.getId());
	}

	public boolean isVisible() {
		return visible;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public void addEntity(Entity entity) {
		entities.put(entity.getId(), entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity.getId());
	}
}
