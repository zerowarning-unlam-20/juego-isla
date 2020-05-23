package commands;

import java.util.Scanner;

import entities.UserCharacter;
import items.Access;

@Command("ir")
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
				for (Access access : personaje.getLocation().getAccesses()) {
					if (access.getDestination().getName().contentEquals(lugar) && access.isOpened()) {
						personaje.goTo(access.getDestination());
						break;
					}
				}
			}
		}
	}

}
