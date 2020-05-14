package entidades;

import java.util.LinkedList;

import ObjetosJuego.Item;
import ObjetosJuego.Ubicacion;
import estados.Estado;
import estados.Normal;

public class Personaje {
	private String nombre;
	private Ubicacion ubicacion;
	private Estado estado;
	private LinkedList<Item> inventario;

	public Personaje(Ubicacion locacion) {
		this.nombre = "Test";
		this.estado = new Normal();
		inventario = new LinkedList<Item>();
		this.ubicacion = locacion;
	}

	public String verAlrededor() {
		return ubicacion.getDescripcion();
	}

	public String verInventario() {
		return inventario.isEmpty() ? "Inventario vacio" : "En el inventario hay " + inventario.toString();
	}

	@Override
	public String toString() {
		return "Nombre=" + nombre + "\n Locacion=" + ubicacion + "\n Estado=" + estado + "\n Inventario=" + inventario;
	}

	public void cambiarUbicacion(Ubicacion destino) {
		if (ubicacion.getUbicaciones().contains(destino)) {
			if (!destino.estaBloqueado()) {
				this.ubicacion = destino;
				System.out.println("Ubicacion cambiada");
				return;
			}
		}
		System.out.println("Ubicacion no cambiada");
	}

	public void ver() {

	}

	public void usar(Item item) {

	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}
}
