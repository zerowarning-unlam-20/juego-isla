package entidades;

public abstract class Item extends ObjetoJuego {
	
	public Item(int id, String nombre, String descripcion, boolean visible) {
		super(id, nombre, descripcion, visible);
	}
	
	public Item() {
	}
	
	public abstract void usar(Item objetivo);

}
