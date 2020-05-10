package items;

public abstract class Item {
	protected String nombre;
	protected String descripcion;

	public Item(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String verItem() {
		return this.getNombre();
	}
	
	public abstract void usar(Item objetivo);

	public static Item parseItem(String datos) {
		String delimitadores = "\\[|\\]";
		String[] valores = datos.split(delimitadores);
		switch (valores[0]) {
		case "liq":
			return new Liquido(valores[1], valores[2]);
		case "rec":
			return new Recipiente(valores[1], valores[2]);
		case "her":
			return new Herramienta(valores[1], valores[2]);
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		return "Item [nombre=" + nombre + "\n -" + descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
