package commands;

import java.util.Scanner;

import entities.Player;

public class EatCommand implements ActionCommand {
	private Player character;

	public EatCommand(Player character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String itemName = "";
		while (args.hasNext()) {
			itemName += " " + args.next();
		}
		itemName = itemName.trim();
		character.eat(itemName);
	}
}
