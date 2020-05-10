package items;

import interfaces.Apertura;
import interfaces.Desbloqueable;
import locaciones.Locacion;

public class Acceso extends Item implements Desbloqueable, Apertura {
	private Locacion destino1;
	private Locacion destino2;
	private boolean abierto;
	private boolean bloqueado;

	public Acceso(String nombre, String descripcion, Locacion destino1, Locacion destino2, boolean bloqueado, boolean abierto) {
		super(nombre, descripcion);
		this.destino1 = destino1;
		this.destino2 = destino2;
		this.bloqueado = bloqueado;
		this.abierto = abierto;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void usar(Item objetivo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void desbloquear() {
		if (bloqueado)
			bloqueado = false;
	}

	@Override
	public String toString() {
		return "[" + this.nombre + " destino1=" + destino1 + " destino2= " + destino2 + ", abierto=" + abierto + ", bloqueado=" + bloqueado + "]";
	}

	@Override
	public void abrir() {
		abierto = true;
	}

	@Override
	public void cerrar() {
		abierto = false;
	}

	public boolean estaBloqueado() {
		return bloqueado;
	}

	public Locacion getDestino1() {
		return destino1;
	}

	public boolean estaAbierto() {
		return abierto;
	}

	public Locacion getDestino2() {
		return destino2;
	}

}
