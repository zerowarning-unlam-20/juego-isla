package commands;

import java.util.Scanner;

import entities.Access;
import entities.UserCharacter;

@Command("abrir")
public class OpenCommand implements ActionCommand {
	UserCharacter character;

	public OpenCommand(UserCharacter character) {
		this.character = character;
	}

	@Override
	public String perform(Scanner args) {
		if (args.hasNext()) {
			String itemName = "";
			while (args.hasNext()) {
				itemName += args.next() + " ";
			}
			itemName = itemName.trim();
			return character.open(itemName);
		}
		return null;
	}
}
