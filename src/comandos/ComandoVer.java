package comandos;

import java.util.Scanner;

import entidades.Acceso;
import entidades.Item;
import entidades.Personaje;
import entidades.Ubicacion;

@Comando("ver")
public class ComandoVer implements Accion {
	Personaje personaje;

	public ComandoVer(Personaje personaje) {
		this.personaje = personaje;
	}

	@Override
	public String hacer(Scanner args) {
		if (args.hasNext()) {
			String objeto = args.next();
			if (objeto.contentEquals("alrededor") || objeto.contentEquals(personaje.getUbicacion().getNombre())) {
				return personaje.verAlrededor();
			}
			if (objeto.contentEquals("inventario")) {
				return personaje.verInventario();
			}
			for (Item item : personaje.getInventario()) {
				if (item.getNombre().contentEquals(objeto)) {
					return item.getDescripcion();
				}
			}
			for (Acceso acceso : personaje.getUbicacion().getAccesos()) {
				if (acceso.getNombre().contentEquals(objeto) || acceso.getDescripcion().contentEquals(objeto)) {
					return acceso.getDescripcion() + ", lleva a " + acceso.getDestino().getNombre();
				}
			}

			for (Item item : personaje.getUbicacion().getItems()) {
				if (item.getNombre().contentEquals(objeto) || item.getDescripcion().contentEquals(objeto)) {
					return item.getDescripcion();
				}
			}
		}
		return "¿Qué querés ver?";
	}

}
