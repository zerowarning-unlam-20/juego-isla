package commands;

import java.util.Scanner;

import entities.Player;

public class UseCommand implements ActionCommand {
	private Player character;

	public UseCommand(Player character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String itemName = "";
		while (args.hasNext()) {
			itemName += args.next() + " ";
		}
		itemName = itemName.trim();
		character.use(itemName);
	}
}
