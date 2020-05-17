package entities;

import interfaces.Opening;

public class Access extends Item implements Opening {
	private boolean opened;
	private boolean locked;
	private int idKey;
	private Location destination;
	private int idDestination;

	public Access(int id, String name, String description, boolean visible, boolean locked, boolean opened,
			Location destination, int idKey) {
		super(id, name, description, visible);
		this.locked = locked;
		this.opened = opened;
		this.destination = destination;
		this.idDestination = destination.getId();
		this.idKey = idKey;
	}

	public Access(int id, String name, String description, boolean visible, boolean locked, boolean opened,
			int idDestination, int idKey) {
		super(id, name, description, visible);
		this.locked = locked;
		this.opened = opened;
		this.idDestination = idDestination;
		this.idKey = idKey;
	}

	public Access() {
	}

	@Override
	public void use(Item objective) {
		if (opened)
			close();
		else
			open();
	}

	public boolean needsKey() {
		return idKey != 0;
	}

	@Override
	public boolean unlock() {
		boolean retorno = false;
		
		if (locked)
			locked = false;
		
		for (Access c : destination.getAccesses()) {
			if (c.getId() == this.id) {
				c.locked = false;
				retorno = true;
				break;
			}
		}
		return retorno;
	}

	@Override
	public String toString() {
		return "[" + this.id + " - " + this.name + " destino=" + this.idDestination + "]";
	}

	@Override
	public boolean open() {
		boolean possible = false;
		if (!locked) {
			opened = true;
			destination.setLocked(false);
			for (Access c : destination.getAccesses()) {
				if (c.getId() == this.id) {
					c.opened = true;
					possible = true;
					break;
				}
			}
		}
		return possible;
	}

	@Override
	public void close() {
		if (opened) {
			opened = false;
			destination.setLocked(true);
			for (Access c : destination.getAccesses()) {
				if (c.getId() == this.id) {
					c.opened = false;
					break;
				}
			}
		}
	}

	public boolean estaBloqueado() {
		return locked;
	}

	public boolean estaAbierto() {
		return opened;
	}

	public Location getDestino() {
		return destination;
	}

	public void setDestination(Location other) {
		this.destination = other;
	}

	public int getIdDestination() {
		return idDestination;
	}

	@Override
	public void recieveObject(GameObject objeto) {
		Item recibido = (Item) objeto;
		if (recibido.getId() == getIdKey()) {
			locked = false;
		}
	}

	public int getIdKey() {
		return idKey;
	}

}
