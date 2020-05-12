package ObjetosJuego;

import java.util.List;

public class Ubicacion extends ObjetoJuego {
	private List<Ubicacion> ubicaciones;
	private List<Item> items;
	private List<Acceso> accesos;
	private boolean bloqueado;

	public Ubicacion(int id, String nombre, String descripcion, boolean visible, List<Ubicacion> ubicaciones,
			List<Item> items, List<Acceso> accesos, boolean bloqueado) {
		super(id, nombre, descripcion, visible);
		this.setUbicaciones(ubicaciones);
		this.items = items;
		this.setAccesos(accesos);
		this.bloqueado = bloqueado;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
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

	public void setAccesos(List<Acceso> accesos) {
		this.accesos = accesos;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

}
