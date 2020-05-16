package comandos;

import java.util.Scanner;

import entidades.Item;
import entidades.Personaje;
import entidades.Recipiente;

@Comando("tomar")
public class ComandoTomar implements Accion {
	private Personaje personaje;

	public ComandoTomar(Personaje personaje) {
		this.personaje = personaje;
	}

	@Override
	public String hacer(Scanner args) {
		String nombreItem = "";
		while (args.hasNext()) {
			nombreItem = args.next();
		}
		for (Item i : personaje.getInventario()) {
			if (i.getClass() == Recipiente.class) {
				Recipiente recipiente = (Recipiente) i;
				if (recipiente.getLiquido().getNombre().contentEquals(nombreItem)) {
					personaje.eliminarItem(i);
					return "Tomando " + nombreItem + " de " + recipiente.getNombre();
				}
			}
		}
		return "No hay nada para tomar";
	}

}
