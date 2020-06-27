package commands;

import java.util.Scanner;

import entities.Entity;

public class OpenCommand implements ActionCommand {
	private Entity character;

	public OpenCommand(Entity character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String itemName = "";
		while (args.hasNext()) {
			itemName += args.next() + " ";
		}
		itemName = itemName.trim();
		character.open(itemName);
	}
}
