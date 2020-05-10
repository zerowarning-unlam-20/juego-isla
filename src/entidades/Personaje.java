package entidades;

import java.util.LinkedList;

import estados.Estado;
import estados.Normal;
import items.Item;
import locaciones.Locacion;

public class Personaje {
	private String nombre;
	private Locacion locacion;
	private Estado estado;
	private LinkedList<Item> inventario;

	public Personaje(Locacion locacion) {
		this.nombre = "Test";
		this.estado = new Normal();
		inventario = new LinkedList<Item>();
		this.locacion = locacion;
	}

	public void verAlrededor() {
		getLocacion().getLocaciones();
	}

	@Override
	public String toString() {
		return "Nombre=" + nombre + "\n Locacion=" + getLocacion() + "\n Estado=" + estado + "\n Inventario="
				+ inventario;
	}

	public void cambiarLocacion(Locacion destino) {
		if(!this.locacion.estaBloqueada(destino))
			this.locacion = destino;
		else System.out.println("No se puede pasar");
	}

	public void ver() {

	}

	public void usar(Item item) {

	}

	public Locacion getLocacion() {
		return locacion;
	}
}
