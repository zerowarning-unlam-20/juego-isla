package entidades;

import interfaces.Remable;
import interfaces.Tirable;

public class Herramienta extends Item implements Tirable, Remable {

	public Herramienta(int id, String nombre, String descripcion, boolean visible) {
		super(id, nombre, descripcion, visible);
	}
	
	public Herramienta() {
		
	}

	@Override
	public void usar(Item objetivo) {
		objetivo.recibirObjeto(this);
	}

	@Override
	public void remar() {
		// TODO Auto-generated method stub
		System.out.println("Remar con que");
	}

	@Override
	public void tirar() {
		// TODO Auto-generated method stub
		System.out.println("Para maestro");
	}

	@Override
	public void recibirObjeto(ObjetoJuego objeto) {
		// TODO Auto-generated method stub
		
	}

}
