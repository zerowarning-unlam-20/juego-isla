package comandos;

import java.util.Scanner;

import entidades.Acceso;
import entidades.Item;
import entidades.Personaje;

@Comando("desbloquear")
public class Desbloquear implements Accion {
	private Personaje personaje;

	public Desbloquear(Personaje personaje) {
		this.personaje = personaje;
	}

	@Override
	public String hacer(Scanner args) {
		if (args.hasNext()) {
			String aDesbloquear = "";
			String aux = "";
			aDesbloquear = args.next();
			if (aDesbloquear == "")
				return "¿Qué querés desbloquear?";

			while (args.hasNext() && aux != "con") {
				aDesbloquear += aux + " ";
				aux = args.next();
			}
			aDesbloquear += aux;
			aDesbloquear = aDesbloquear.trim();

			for (Item item : personaje.getInventario())
				for (Acceso acceso : personaje.getUbicacion().getAccesos())
					if (acceso.getNombre().contentEquals(aDesbloquear)) {
						acceso.recibirObjeto(item);
						if (!acceso.estaBloqueado()) {
							personaje.eliminarItem(item);
							return aDesbloquear + " se abrió";
						}
					}
		}
		return "No se desbloqueó nada";
	}

}
