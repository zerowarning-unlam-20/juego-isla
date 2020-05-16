package comandos;

import java.util.Scanner;

import entidades.Acceso;
import entidades.Personaje;

@Comando("abrir")
public class ComandoAbrir implements Accion {
	Personaje personaje;

	public ComandoAbrir(Personaje personaje) {
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
			for (Acceso a : personaje.getUbicacion().getAccesos()) {
				if (a.getNombre().contentEquals(nombreItem) || a.getDescripcion().contentEquals(nombreItem)) {
					return a.abrir() ? "Se abrio: " + a.getNombre() : "No se abrio: " + a.getNombre();
				}
			}
		}
		return null;
	}
}
