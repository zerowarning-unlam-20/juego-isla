package commands;

import java.util.Scanner;

import entities.UserCharacter;
import items.Item;
import items.types.Container;

public class DrinkCommand implements ActionCommand {
	private UserCharacter character;

	public DrinkCommand(UserCharacter personaje) {
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
		Item item = null;
		for (Item i : character.getInventory()) {
			if (i.getClass() == Container.class) {
				Container container = (Container) i;
				if (!container.isEmpty() && container.getContent().getName().equalsIgnoreCase(itemName)) {
					item = i;
					break;
				}
			} else if (i.getName().equalsIgnoreCase(itemName)) {
				item = i;
				break;
			}
		}
		character.drink(item);
	}

}
