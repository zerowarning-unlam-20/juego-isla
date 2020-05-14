package comandos;

import java.util.Scanner;

import entidades.Personaje;
import entidades.Ubicacion;

@Comando("ir")
public class Ir implements Accion{
	Personaje personaje;
	public Ir(Personaje personaje) {
		this.personaje = personaje;
	}

	@Override
	public String hacer(Scanner args) {
		String lugar = "";
		if(args.hasNext()) {
			args.next();
			while(args.hasNext()) {
				lugar += args.next() + " ";	
			}
			lugar = lugar.trim();
			if(lugar != "") {
				for(Ubicacion destino : personaje.getUbicacion().getUbicaciones()) {
					if(destino.getNombre().contentEquals(lugar)) {
						personaje.cambiarUbicacion(destino);
						return "me fui a " + destino.getNombre();
					}
				}
			}
		}
		return personaje.getUbicacion().getDescripcion();
	}
	
}
