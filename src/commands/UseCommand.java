package commands;

import java.util.Scanner;

import entities.Player;
import items.Item;

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
		use(itemName);
	}

	private void use(String itemName) {
		Item item = character.getInventory().get(itemName);
		character.use(item);
	}
}
