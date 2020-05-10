package locaciones;

import java.util.List;

import items.Acceso;
import items.Item;

public class Locacion {
	private String nombre;
	private String descripcion;
	private List<Locacion> locaciones;
	private List<Item> items;
	private List<Acceso> accesos;

	public Locacion(String nombre, String descripcion, List<Locacion> locaciones, List<Item> items,
			List<Acceso> accesos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.locaciones = locaciones;
		this.items = items;
		this.accesos = accesos;
	}

	public List<Locacion> getLocaciones() {
		return locaciones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return "[" + nombre + "]";
	}

	public List<Item> getItems() {
		return items;
	}

	public boolean estaBloqueada(Locacion destino) {
		boolean abierto = false;
		for (Acceso a : getAccesos()) {
			if (a.getDestino1() == destino || a.getDestino2() == destino) {
				abierto = a.estaAbierto();
			}
		}
		return !abierto;
	}

	public List<Acceso> getAccesos() {
		return accesos;
	}

}
