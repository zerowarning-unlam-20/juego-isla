package ObjetosJuego;

public abstract class ObjetoJuego {
	protected int id;
	protected String nombre;
	protected String descripcion;
	protected boolean visible;
	
	public ObjetoJuego(int id, String nombre, String descripcion, boolean visible) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.visible = visible;
	}
	
	public ObjetoJuego() {
		nombre = "";
		descripcion = "";
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	@Override
	public String toString() {
		return "ObjetoJuego [nombre=" + nombre + "\n -" + descripcion + "]";
	}
}
