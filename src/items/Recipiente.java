package items;

public class Recipiente extends Item {
	private Item liquido;

	public Recipiente(String nombre, String descripcion) {
		super(nombre, descripcion);
	}

	public Item getLiquido() {
		return liquido;
	}

	@Override
	public String toString() {
		String result;
		if (liquido != null)
			result = super.toString() + "\n tiene: " + liquido;
		else
			result = super.toString() + "\n vacio";
		return result;

	}

	@Override
	public void usar(Item objetivo) {
		// TODO Auto-generated method stub
	}
}
