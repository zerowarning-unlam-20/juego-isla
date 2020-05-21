package commands;

import java.util.List;
import java.util.Scanner;

import entities.UserCharacter;
import items.Container;
import items.Item;
import items.SingleContainer;

@Command("agarrar")
public class GrabCommand implements ActionCommand {
	private UserCharacter character;

	public GrabCommand(UserCharacter character) {
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
			grab(itemName);
		}
	}

	private void grab(String itemName) {
		List<Item> items = character.getLocation().getItems();
		Item item = null;
		for (Item i : items) {
			if (i.getClass() == Container.class && !i.getName().contentEquals(itemName)) {
				item = ((Container) i).getFromContent(itemName);
				break;
			} else if (i.getClass() == SingleContainer.class && !i.getName().contentEquals(itemName)) {
				item = ((Container) i).getFromContent(itemName);
				break;
			} else if (i.getName().contentEquals(itemName)) {
				item = i;
				break;
			}
			character.grab(item);
		}
	}
}
