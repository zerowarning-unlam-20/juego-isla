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

	public void verAlrededor() {
		ubicacion.getLocaciones();
	}

	@Override
	public String toString() {
		return "Nombre=" + nombre + "\n Locacion=" + ubicacion + "\n Estado=" + estado + "\n Inventario="
				+ inventario;
	}

	public void cambiarUbicacion(Ubicacion destino) {
        if(!destino.estaBloqueado()) {
            this.ubicacion = destino;
        }
    }

	public void ver() {

	}

	public void usar(Item item) {

	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}
}
