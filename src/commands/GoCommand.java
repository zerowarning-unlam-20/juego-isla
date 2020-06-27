package commands;

import java.util.Scanner;

import entities.Entity;

public class GoCommand implements ActionCommand {
	private Entity character;

	public GoCommand(Entity character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String lugar = "";

		while (args.hasNext()) {
			lugar += args.next() + " ";
		}
		lugar = lugar.trim();
		character.goTo(lugar);
	}

}
