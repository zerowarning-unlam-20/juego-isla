package commands;

import java.util.Scanner;

import entities.Entity;

public class ReadCommand implements ActionCommand {
	private Entity character;

	public ReadCommand(Entity character) {
		this.character = character;
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
