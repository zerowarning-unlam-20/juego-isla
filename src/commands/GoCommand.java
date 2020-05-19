package commands;

import java.util.Scanner;

import entities.UserCharacter;
import entities.Location;

@Command("ir")
public class GoCommand implements ActionCommand {
	UserCharacter personaje;

	public GoCommand(UserCharacter personaje) {
		this.personaje = personaje;
	}

	@Override
	public String perform(Scanner args) {
		String stringToReturn = null;
		String lugar = "";
		
		if (args.hasNext()) {
			args.next();
			while (args.hasNext()) {
				lugar += args.next() + " ";
			}
			lugar = lugar.trim();
			if (lugar != "") {
				stringToReturn = personaje.goTo(lugar);
			}
		}
		return (stringToReturn != null)? stringToReturn : personaje.getLocation().getDescription();
	}

}
