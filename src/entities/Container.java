package entities;

public class Container extends Item {
	private Item contenido;

	public Container(int id, String nombre, String descripcion, boolean visible) {
		super(id, nombre, descripcion, visible);
	}

	public Container(int id, String nombre, String descripcion, boolean visible, Item contenido) {
		super(id, nombre, descripcion, visible);
		this.contenido = contenido;
		descripcion = nombre + " con " + contenido.name;
	}

	public Item getContent() {
		return contenido;
	}

	public void setContenido(Item contenido) {
		this.contenido = contenido;
		description = name + " con " + contenido.name;
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
	public void use(Item objetivo) {
		objetivo.recieveObject(contenido);
	}

	@Override
	public void recieveObject(GameObject objeto) {
		contenido = (Item) objeto;
		description = name + " con " + objeto.name;
	}

	public void vaciar() {
		contenido = null;
	}
}
