package commands;

import java.util.Scanner;

import entities.Player;

public class ReadCommand implements ActionCommand {
	private Player character;

	public ReadCommand(Player personaje) {
		this.character = personaje;
	}

	@Override
	public void perform(Scanner args) {
		String name = "";
		while (args.hasNext()) {
			name += args.next();
		}

		character.read(name);
	}
}
