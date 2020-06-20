package items;

import entities.Attack;
import island.Location;
import items.properties.Attackable;
import items.properties.Opening;
import items.properties.Unlockable;
import tools.DamageType;
import tools.Gender;

public class Access extends Item implements Opening, Unlockable, Attackable {
	private boolean opened;
	private boolean locked;
	private int idKey;
	private Location destination;
	private int idDestination;
	private Access linkedWith;
	private DamageType weakness;

	public Access(int id, Gender gender, String name, String description, boolean locked, boolean opened,
			Location destination, int idKey, DamageType weakness) {
		super(id, gender, name, description);
		this.locked = locked;
		this.opened = opened;
		this.destination = destination;
		this.idDestination = destination.getId();
		this.idKey = idKey;
		this.weakness = weakness;
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
	public void unlock() { // Solamente desbloquea
		locked = false;
		if (linkedWith != null)
			linkedWith.locked = false;
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

	@Override
	public boolean unlock(Item keyItem) { // desbloquear con una llave en particular
		boolean result = false;
		if (locked) {
			if (keyItem.getId() == idKey) {
				locked = false;
			}
			if (linkedWith != null)
				linkedWith.locked = false;
			result = true;
		}
		return result;
	}

	@Override
	public boolean recieveAttack(Attack attack) { // Si el acceso es rompible bueno, se rompe
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
