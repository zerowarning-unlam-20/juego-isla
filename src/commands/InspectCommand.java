package commands;

import java.util.Scanner;

import entities.Player;

public class InspectCommand implements ActionCommand {
	private Player character;

	public InspectCommand(Player character) {
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
