package ObjetosJuego;

import java.util.List;

public class Ubicacion extends ObjetoJuego{
	private List<Ubicacion> locaciones;
	private List<Item> items;
	private List<Acceso> accesos;
	private boolean bloqueado;

	public Ubicacion(int id, String nombre, String descripcion, boolean visible, List<Ubicacion> locaciones, List<Item> items,
			List<Acceso> accesos, boolean bloqueada) {
		super(id, nombre, descripcion, visible);
		this.locaciones = locaciones;
		this.items = items;
		this.accesos = accesos;
		this.bloqueado = bloqueada;
	}

	public List<Ubicacion> getLocaciones() {
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

	public List<Acceso> getAccesos() {
		return accesos;
	}

	public boolean estaBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueada) {
		this.bloqueado = bloqueada;
	}

}
