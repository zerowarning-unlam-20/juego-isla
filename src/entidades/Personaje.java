package entidades;

import java.util.LinkedList;

import ObjetosJuego.Item;
import ObjetosJuego.Ubicacion;
import estados.Estado;
import estados.Normal;

public class Personaje {
	private String nombre;
	private Ubicacion locacion;
	private Estado estado;
	private LinkedList<Item> inventario;

	public Personaje(Ubicacion locacion) {
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

	public void cambiarLocacion(Ubicacion destino) {
		
	}

	public void ver() {

	}

	public void usar(Item item) {

	}

	public Ubicacion getLocacion() {
		return locacion;
	}
}
