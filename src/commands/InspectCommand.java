package commands;

import java.util.Scanner;

import entities.Entity;

public class InspectCommand implements ActionCommand {
	private Entity character;

	public InspectCommand(Entity character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String name = "";
		while (args.hasNext()) {
			name += args.next();
		}
		character.inspect(name);
	}

}
