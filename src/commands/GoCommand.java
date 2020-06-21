package commands;

import java.util.Scanner;

import entities.Player;

public class GoCommand implements ActionCommand {
	private Player personaje;

	public GoCommand(Player personaje) {
		this.personaje = personaje;
	}

	@Override
	public void perform(Scanner args) {
		String lugar = "";

		while (args.hasNext()) {
			lugar += args.next() + " ";
		}
		lugar = lugar.trim();
		personaje.goTo(lugar);
	}

}
