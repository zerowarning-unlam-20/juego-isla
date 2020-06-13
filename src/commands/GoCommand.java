package commands;

import java.util.Scanner;

import entities.UserCharacter;
import island.Location;
import manager.Game;

public class GoCommand implements ActionCommand {
	private UserCharacter personaje;

	public GoCommand(UserCharacter personaje) {
		this.personaje = personaje;
	}

	@Override
	public void perform(Scanner args) {
		String lugar = "";

		if (args.hasNext()) {
			args.next();
			while (args.hasNext()) {
				lugar += args.next() + " ";
			}
			lugar = lugar.trim();
			if (lugar != "") {
				Location location = Game.getLocation(lugar);
				if (location != null) {
					personaje.goTo(location.getId());
				}
			}
		}
	}

}
