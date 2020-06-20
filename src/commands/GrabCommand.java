package commands;

import java.util.List;
import java.util.Scanner;

import entities.UserCharacter;
import items.Item;
import items.types.Container;
import items.types.Source;

public class GrabCommand implements ActionCommand {
	private UserCharacter character;

	public GrabCommand(UserCharacter character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String itemName = "";
		while (args.hasNext()) {
			itemName += args.next() + " ";
		}
		itemName = itemName.trim();
		grab(itemName);
	}
	
	private void grab(String itemName) {
		List<Item> items = character.getLocation().getItems();
		Item item = null;
		for (Item i : items) {
			if (i instanceof Container
					&& (!i.getName().equalsIgnoreCase(itemName) || i.getDescription().equalsIgnoreCase(itemName))) {
				item = ((Container) i).getContent();
				if (item.getName().equalsIgnoreCase(itemName)) {
					item = i;
				}
				break;
			} else if (i instanceof Source
					&& (!i.getName().equalsIgnoreCase(itemName) || i.getDescription().equalsIgnoreCase(itemName))) {
				item = ((Source) i).getContent();
				if (item.getName().equalsIgnoreCase(itemName)) {
					item = i;
				}
				break;
			} else if (i.getName().equalsIgnoreCase(itemName) || i.getDescription().equalsIgnoreCase(itemName)) {
				item = i;
				break;
			}
		}
		character.grab(item);
	}
}
