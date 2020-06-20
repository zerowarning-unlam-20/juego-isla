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
		String itemName = "";
		while (args.hasNext()) {
			itemName += args.next() + " ";
		}
		itemName = itemName.trim();
		open(itemName);
	}

	private void open(String itemName) {
		Access result = null;
		if (!itemName.isEmpty()) {
			for (Access a : character.getLocation().getAccesses().values())
				if (a.getName().equalsIgnoreCase(itemName) || a.getDescription().equalsIgnoreCase(itemName)) {
					result = a;
					break;
				}
		}
		character.open(result);
	}
}
