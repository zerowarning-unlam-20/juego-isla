package commands;

import java.util.List;
import java.util.Scanner;

import entities.UserCharacter;
import items.Item;
import items.properties.Usable;

public class UseCommand implements ActionCommand {
	private UserCharacter character;

	public UseCommand(UserCharacter character) {
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
		List<Item> items = character.getInventory();
		Item item = null;
		for (Item i : items) {
			if (i instanceof Usable && i.getName().equalsIgnoreCase(itemName)) {
				item = i;
				break;
			}
		}

		character.use(item);
	}
}
