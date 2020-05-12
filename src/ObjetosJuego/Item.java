package ObjetosJuego;

public abstract class Item extends ObjetoJuego {

	public Item(int id, String nombre, String descripcion, boolean visible) {
		super(id, nombre, descripcion, visible);
	}

	public Item(int id, String nombre, String descripcion) {
		super(id, nombre, descripcion);
	}
	
	public String verItem() {
		return this.getNombre();
	}
	
	public abstract void usar(Item objetivo);

}
