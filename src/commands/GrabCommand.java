package commands;

import java.util.List;
import java.util.Scanner;

import entities.UserCharacter;
import entities.Item;

@Command("agarrar")
public class GrabCommand implements ActionCommand {
	private UserCharacter personaje;

	public GrabCommand(UserCharacter personaje) {
		this.personaje = personaje;
	}

	@Override
	public String perform(Scanner args) {
		if (args.hasNext()) {
			String nombreItem = "";
			while (args.hasNext()) {
				nombreItem += args.next() + " ";
			}
			nombreItem = nombreItem.trim();
			
			return personaje.grab(nombreItem);
		}
		return "¿Qué querés agarrar?";
	}

}
