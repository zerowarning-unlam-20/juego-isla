package commands;

import java.util.List;
import java.util.Scanner;

import entities.UserCharacter;
import items.Consumable;
import items.Container;
import items.Item;
import items.SingleContainer;

public class UseCommand implements ActionCommand {
	private UserCharacter character;

	public UseCommand(UserCharacter character) {
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
			use(itemName);
		}
	}

	private void use(String itemName) {
		List<Item> items = character.getInventory();
		Item item = null;
		for (Item i : items) {
			if (i.getClass() == Consumable.class) {
				item = !((Consumable) i).needsContainer() ? i : null;
				break;
			}
		}
		character.use(item);
	}
}
