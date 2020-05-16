package comandos;

import java.util.List;
import java.util.Scanner;

import entidades.Item;
import entidades.Personaje;

@Comando("agarrar")
public class ComandoAgarrar implements Accion {
	private Personaje personaje;

	public ComandoAgarrar(Personaje personaje) {
		this.personaje = personaje;
	}

	@Override
	public String hacer(Scanner args) {
		if (args.hasNext()) {
			String nombreItem = "";
			while (args.hasNext()) {
				nombreItem += args.next() + " ";
			}
			nombreItem = nombreItem.trim();
			List<Item> items = personaje.getUbicacion().getItems();
			for (Item i : items) {
				if (i.getNombre().contentEquals(nombreItem)) {
					personaje.agregarItem(i);
					nombreItem = i.getDescripcion();
					personaje.getUbicacion().eliminarItem(i);
					return "Se agarró " + nombreItem;
				}
			}
			return "No se pudo agarrar";
		}
		return "¿Qué querés agarrar?";
	}

}
