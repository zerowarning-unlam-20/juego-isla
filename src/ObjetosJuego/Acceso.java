package ObjetosJuego;

import interfaces.Apertura;

public class Acceso extends Item implements Apertura {
	private boolean abierto;
	private boolean bloqueado;
	private int idLlave;
	private Ubicacion destino;
	private int idDestino;

	public Acceso(int id, String nombre, String descripcion, boolean visible, boolean bloqueado, boolean abierto,
			Ubicacion destino, int idLlave) {
		super(id, nombre, descripcion, visible);
		this.bloqueado = bloqueado;
		this.abierto = abierto;
		this.destino = destino;
		this.idDestino = destino.getId();
		this.idLlave = idLlave;
	}

	public Acceso(int id, String nombre, String descripcion, boolean visible, boolean bloqueado, boolean abierto,
			int idDestino, int idLlave) {
		super(id, nombre, descripcion, visible);
		this.bloqueado = bloqueado;
		this.abierto = abierto;
		this.idDestino = idDestino;
		this.idLlave = idLlave;
	}

	public Acceso() {
	}

	@Override
	public void usar(Item objetivo) {
		if (abierto)
			cerrar();
		else
			abrir();
	}

	public boolean necesitaLlave() {
		return idLlave != 0;
	}

	@Override
	public boolean desbloquear() {
		if (bloqueado)
			bloqueado = false;
		for (Acceso c : destino.getAccesos()) {
			if (c.getId() == this.id) {
				c.bloqueado = false;
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "[" + this.id + " - " + this.nombre + " destino=" + this.idDestino + "]";
	}

	@Override
	public boolean abrir() {
		boolean posible = false;
		if (!bloqueado) {
			abierto = true;
			destino.setBloqueado(false);
			for (Acceso c : destino.getAccesos()) {
				if (c.getId() == this.id) {
					c.abierto = true;
					posible = true;
					break;
				}
			}
		}
		return posible;
	}

	@Override
	public void cerrar() {
		if (abierto) {
			abierto = false;
			destino.setBloqueado(true);
			for (Acceso c : destino.getAccesos()) {
				if (c.getId() == this.id) {
					c.abierto = false;
					break;
				}
			}
		}
	}

	public boolean estaBloqueado() {
		return bloqueado;
	}

	public boolean estaAbierto() {
		return abierto;
	}

	public Ubicacion getDestino() {
		return destino;
	}

	public void setDestino(Ubicacion otro) {
		this.destino = otro;
	}

	public int getIdDestino() {
		return idDestino;
	}

}
