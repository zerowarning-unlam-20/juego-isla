package commands;

import java.util.Scanner;

import entities.UserCharacter;
import items.Item;

public class CreateCommand implements ActionCommand {
	UserCharacter character;

	@Override
	public void perform(Scanner args) {
		String itemName = "";
		while (args.hasNext()) {
			itemName = args.next();
		}
		Item item = character.getInventoryStringMap().get("plano " + itemName);
		character.create(item);
	}

}
