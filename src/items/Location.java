package items;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import island.GameObject;
import tools.Gender;

public class Location extends GameObject {
	private boolean visible;
	private List<Item> items;
	private List<Access> accesses;
	private List<Entity> npcs;

	public Location() {
	}

	public Location(int id, Gender gender, String name, String description, boolean visible, List<Item> items,
			List<Access> accesses, boolean locked) {
		super(id, gender, name, description);
		this.items = items;
		this.visible = visible;
		this.accesses = accesses;
	}

	public Location(int id, Gender gender, String name, String description, boolean visible, List<Item> items) {
		super(id, gender, name, description);
		this.items = new ArrayList<Item>();
		this.accesses = new ArrayList<Access>();
		this.items = items;
		this.visible = visible;
	}

	public Location(int id, Gender gender, String name, String description, boolean visible) {
		super(id, gender, name, description);
		this.items = new ArrayList<Item>();
		this.accesses = new ArrayList<Access>();
		this.visible = visible;
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

	public void setAccesses(List<Access> accesses) {
		this.accesses = accesses;
	}

	public void addAccess(Access access) {
		accesses.add(access);
	}

	public boolean addLink(Location other) {

		if (other != null) {
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

}
