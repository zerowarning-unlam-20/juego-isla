package entities;

import java.util.ArrayList;
import java.util.List;

public class Location extends GameObject {
	private List<Item> items;
	private List<Access> accesses;
	private List<Entity> npcs;
	private boolean locked;

	public Location() {
	}

	public Location(int id, String name, String description, boolean visible, List<Item> items, List<Access> accesses,
			boolean locked) {
		super(id, name, description, visible);
		this.items = items;
		this.accesses = accesses;
		this.locked = locked;
	}

	public Location(int id, String name, String description, boolean visible, boolean locked, List<Item> items) {
		super(id, name, description, visible);
		this.items = new ArrayList<Item>();
		this.accesses = new ArrayList<Access>();
		this.items = items;
		this.locked = locked;
	}

	public Location(int id, String name, String description, boolean visible, boolean locked) {
		super(id, name, description, visible);
		this.items = new ArrayList<Item>();
		this.accesses = new ArrayList<Access>();
		this.locked = locked;
	}

	public List<Location> getLocations() {
		ArrayList<Location> locations = new ArrayList<>();
		for (Access access : accesses) {
			locations.add(access.getDestination());
		}
		return locations;
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

	public List<Access> getAccesses() {
		return accesses;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setAccesses(List<Access> accesses) {
		this.accesses = accesses;
	}

	public void addAccess(Access access) {
		accesses.add(access);
	}

	public boolean addLink(Location other) {
		if (other == null)
			return false;
		for (Access a : accesses) {
			if (a.getIdDestination() == other.id) {
				a.setDestination(other);
			}
		}
		for (Access b : other.accesses) {
			if (b.getIdDestination() == this.id) {
				b.setDestination(this);
			}
		}
		return false;
	}

	@Override
	public void recieveObject(GameObject object) {
		// TODO Auto-generated method stub
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

}
