package items;

import interfaces.Opening;
import interfaces.Unlockable;
import island.GameObject;
import tools.Gender;

public class Access extends GameObject implements Opening, Unlockable {
	private boolean opened;
	private boolean locked;
	private int idKey;
	private Location destination;
	private int idDestination;
	private Access linkedWith;

	public Access(int id, Gender gender, String name, String description, boolean locked, boolean opened,
			Location destination, int idKey) {
		super(id, gender, name, description);
		this.locked = locked;
		this.opened = opened;
		this.destination = destination;
		this.idDestination = destination.getId();
		this.idKey = idKey;
	}

	public Access(int id, Gender gender, String name, String description, boolean locked, boolean opened,
			int idDestination, int idKey) {
		super(id, gender, name, description);
		this.locked = locked;
		this.opened = opened;
		this.idDestination = idDestination;
		this.idKey = idKey;
	}

	public void linkWith(Access other) {
		if (other != null) {
			this.linkedWith = other;
			other.linkedWith = this;
		}
	}

	public boolean needsKey() {
		return idKey != 0;
	}

	@Override
	public boolean unlock() {
		boolean result = false;
		if (locked) {
			locked = false;
			if (linkedWith != null)
				linkedWith.locked = false;
			result = true;
		}
		return result;
	}

	@Override
	public String toString() {
		return "[" + this.id + " - " + this.name + " destino=" + this.idDestination + "]";
	}

	@Override
	public boolean open() {
		boolean possible = false;
		if (!locked) {
			if (opened) {
				possible = false;
			} else {
				opened = true;
				possible = true;
			}
		}
		return possible;
	}

	@Override
	public void close() {
		if (opened) {
			opened = false;
		}
	}

	public boolean isLocked() {
		return locked;
	}

	public boolean isOpened() {
		return opened;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location other) {
		this.destination = other;
	}

	public int getIdDestination() {
		return idDestination;
	}

	public String getStatus() {
		String result = "";
		if (locked)
			result = "bloquead";
		else if (opened) {
			result = "abiert";
		} else
			result = "cerrad";
		return result + gender.getTermination();
	}

	public boolean unlockKey(Item recieved) {
		if (recieved != null) {
			if (recieved.getId() == getIdKey()) {
				locked = false;
				return true;
			}
		}
		return false;
	}

	public int getIdKey() {
		return idKey;
	}

}
