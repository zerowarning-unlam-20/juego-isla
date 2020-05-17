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
		String retorno = null;
		if (args.hasNext()) {
			String itemName = "";
			while (args.hasNext()) {
				itemName += args.next() + " ";
			}
			itemName = itemName.trim();
			retorno = character.open(itemName);
		}
		return retorno;
	}
}
