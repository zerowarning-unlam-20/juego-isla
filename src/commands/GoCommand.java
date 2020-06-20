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

		while (args.hasNext()) {
			lugar += args.next() + " ";
		}
		lugar = lugar.trim();
		Location location = personaje.getGameManager().getGame().getLocationFromString(lugar);
		if (location == null)
			personaje.goTo(null);
		else
			personaje.goTo(location.getId());
	}

}
