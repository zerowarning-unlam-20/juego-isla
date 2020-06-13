package commands;

import java.util.Scanner;

import entities.UserCharacter;
import island.Location;

public class GoCommand implements ActionCommand {
	private UserCharacter personaje;

	public GoCommand(UserCharacter personaje) {
		this.personaje = personaje;
	}

	@Override
	public void perform(Scanner args) {
		String lugar = "";
		
		if (args.hasNext()) {
			while (args.hasNext()) {
				lugar += args.next() + " ";
			}
			lugar = lugar.trim();
			if (lugar != "") {
				Location location = personaje.getGameManager().getGame().getLocationFromString(lugar);
				if (location != null) {
					personaje.goTo(location.getId());
				}
			}
		}
	}

}
