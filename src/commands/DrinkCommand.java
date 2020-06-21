package commands;

import java.util.Scanner;

import entities.Player;
import items.Item;

public class DrinkCommand implements ActionCommand {
	private Player character;

	public DrinkCommand(Player personaje) {
		this.character = personaje;
	}

	@Override
	public void perform(Scanner args) {
		String itemName = "";
		while (args.hasNext()) {
			itemName = args.next();
		}
		drink(itemName.trim());
	}

	private void drink(String itemName) {
		Item item = character.getInventory().get(itemName);
		character.drink(item);
	}

}
