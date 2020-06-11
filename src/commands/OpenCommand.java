package commands;

import java.util.Scanner;

import entities.UserCharacter;
import items.Access;

public class OpenCommand implements ActionCommand {
	private UserCharacter character;

	public OpenCommand(UserCharacter character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		if (args.hasNext()) {
			String itemName = "";

			while (args.hasNext()) {
				itemName += args.next() + " ";
			}

			itemName = itemName.trim();
			open(itemName);
		}
	}

	private void open(String itemName) {
		for (Access a : character.getLocation().getAccesses().values())
			if (a.getName().contentEquals(itemName) || a.getDescription().contentEquals(itemName)) {
				character.open(a);
				break;
			}
	}
}
