package commands;

import java.util.Scanner;

import entities.Entity;

public class DropCommand implements ActionCommand {
	private Entity character;

	public DropCommand(Entity character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String item = args.next();
		item = item.trim();
		character.drop(item);
	}

}
