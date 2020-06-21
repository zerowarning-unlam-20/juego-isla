package commands;

import java.util.Scanner;

import entities.Player;
import items.Item;
import items.types.Source;

public class GrabCommand implements ActionCommand {
	private Player character;

	public GrabCommand(Player character) {
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

	private void grab(String name) {
		Item item = character.getLocation().getItems().get(name);
		Item content = null;
		if (item == null) {
			for (Item i : character.getLocation().getItems().values()) {
				if (i instanceof Source && ((Source) i).getContent().getName().equalsIgnoreCase(name)) {
					item = i;
					content = ((Source) i).getContent();
					break;
				}
			}
		}
		character.grab(item, content);
	}
}
