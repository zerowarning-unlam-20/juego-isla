package comandos;

import java.util.Scanner;

import entidades.Personaje;
import entidades.Ubicacion;

@Comando("ver")
public class Ver implements Accion {
	Personaje personaje;

	public Ver(Personaje personaje) {
		this.personaje = personaje;
	}

	@Override
	public String hacer(Scanner args) {
		if (args.hasNext()) {
			String objeto = args.next();
			if (objeto.contentEquals("alrededor") || objeto.contentEquals(personaje.getUbicacion().getNombre())) {
				return personaje.verAlrededor();
			}
			if(objeto.contentEquals("inventario")) {
				return personaje.verInventario();
			}
		}
		return "¿Qué querés ver?";
	}

}
