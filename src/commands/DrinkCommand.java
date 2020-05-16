package commands;

import java.util.Scanner;

import entities.UserCharacter;
import entities.Container;
import entities.Item;

@Command("tomar")
public class DrinkCommand implements ActionCommand {
	private UserCharacter character;

	public DrinkCommand(UserCharacter personaje) {
		this.character = personaje;
	}

	@Override
	public String perform(Scanner args) {
		String itemName = "";
		while (args.hasNext()) {
			itemName = args.next();
		}
		return character.drink(itemName);
	}

}
