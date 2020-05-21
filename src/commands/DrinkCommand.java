package commands;

import java.util.Scanner;

import entities.UserCharacter;
import items.Item;
import items.SingleContainer;
import manager.GameManager;

@Command("tomar")
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
		for (Item i : character.getInventory()) {
			if (i.getClass() == SingleContainer.class) {
				SingleContainer container = (SingleContainer) i;
				if (container.getContent().getName().contentEquals(itemName)) {
					character.drink(i);
					break;
				}
			} else if (i.getName().contentEquals(itemName)) {
				character.drink(i);
				break;
			}
		}
	}

}
