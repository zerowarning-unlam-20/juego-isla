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
	private String keyName;
	private Location destination;
	private String destinationName;
	private Access linkedWith;
	private DamageType weakness;

	public Access(Gender gender, String name, String description, boolean locked, boolean opened, Location destination,
			String destinationName, String idKey, DamageType weakness) {
		super(gender, name, description);
		this.locked = locked;
		this.opened = opened;
		this.destination = destination;
		this.destinationName = destinationName;
		this.keyName = idKey;
		this.weakness = weakness;
	}

	// TODO: revisar linkeado de "una puerta" que conecta dos lugares, hace mucho se
	// dejó de lado
	public void linkWith(Access other) {
		if (other != null) {
			this.linkedWith = other;
			other.linkedWith = this;
		}
	}

	public boolean needsKey() {
		return getKeyName() != "";
	}

	@Override
	public void unlock() { // Solamente desbloquea
		locked = false;
		if (linkedWith != null)
			linkedWith.locked = false;
	}

	@Override
	public String toString() {
		return "[" + name + " destino=" + this.destinationName + "]";
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

	public String getDestinationName() {
		return destinationName;
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
			if (keyItem.getName().equalsIgnoreCase(getKeyName())) {
				locked = false;
			}
			if (linkedWith != null)
				linkedWith.locked = false;
			result = true;
		}
		return result;
	}

	// Recibir un ataque es como poner una llave, nada mas que con attack.
	// Dependiendo del tipo de daño se va a desbloquear esto
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

	public String getKeyName() {
		return keyName;
	}

}
