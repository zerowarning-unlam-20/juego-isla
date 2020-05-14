package entidades;

public class Recipiente extends Item {
	private Item contenido;

	public Recipiente(int id, String nombre, String descripcion, boolean visible) {
		super(id, nombre, descripcion, visible);
	}
	
	public Recipiente(int id, String nombre, String descripcion, boolean visible, Item contenido) {
		super(id, nombre, descripcion, visible);
		this.contenido = contenido;
		descripcion = nombre + " con " + contenido.nombre;
	}

	public Item getLiquido() {
		return contenido;
	}
	
	public void setContenido(Item contenido) {
		this.contenido = contenido;
		descripcion = nombre + " con " + contenido.nombre;
	}

	@Override
	public String toString() {
		String result;
		if (contenido != null)
			result = super.toString() + "\n tiene: " + contenido;
		else
			result = super.toString() + "\n vacio";
		return result;

	}

	@Override
	public void usar(Item objetivo) {
		 objetivo.recibirObjeto(contenido);
	}
	
	@Override
	public void recibirObjeto(ObjetoJuego objeto) {
		contenido = (Item) objeto;
		descripcion = nombre + " con " + objeto.nombre;
	}

	public void vaciar() {
		contenido = null;
	}
}
