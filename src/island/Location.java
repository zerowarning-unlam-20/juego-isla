package island;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.Entity;
import entities.NPC;
import items.Access;
import items.Item;
import tools.Gender;

public class Location extends GameObject {
	private boolean visible;
	private List<Item> items;
	private HashMap<Integer, Access> accesses;
	private HashMap<Integer, Entity> entities;

	public Location() {
	}

	public Location(int id, Gender gender, String name, String description, boolean visible, List<Item> items,
			HashMap<Integer, Access> accesses) {
		super(id, gender, name, description);
		this.items = items;
		this.visible = visible;
		this.entities = new HashMap<Integer, Entity>();
		this.accesses = accesses;
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
