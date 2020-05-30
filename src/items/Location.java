package items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.Entity;
import island.GameObject;
import tools.Gender;

public class Location extends GameObject {
	private boolean visible;
	private List<Item> items;
	private HashMap<Integer, Access> accesses;
	private List<Entity> npcs;

	public Location() {
	}

	public Location(int id, Gender gender, String name, String description, boolean visible, List<Item> items,
			HashMap<Integer, Access> accesses, boolean locked) {
		super(id, gender, name, description);
		this.items = items;
		this.visible = visible;
		this.accesses = accesses;
	}

	public Location(int id, Gender gender, String name, String description, boolean visible, List<Item> items) {
		super(id, gender, name, description);
		this.items = new ArrayList<Item>();
		this.accesses = new HashMap<Integer, Access>();
		this.items = items;
		this.visible = visible;
	}

	public Location(int id, Gender gender, String name, String description, boolean visible) {
		super(id, gender, name, description);
		this.items = new ArrayList<Item>();
		this.accesses = new HashMap<Integer, Access>();
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

	@Override
	public boolean recieveObject(GameObject object) {
		return false;
	}

	public void removeItem(Item i) {
		items.remove(i);
	}

	public List<Entity> getNpcs() {
		return npcs;
	}

	public void setNpcs(List<Entity> npcs) {
		this.npcs = npcs;
	}

	@Override
	public void recieveDamage(Double damage) {
		// TODO Auto-generated method stub

	}

	public boolean isVisible() {
		return visible;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

}
