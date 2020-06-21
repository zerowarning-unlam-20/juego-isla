package commands;

import java.util.List;
import java.util.Scanner;

import entities.Player;
import items.Item;
import items.types.Container;
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

	private void grab(String itemName) {
		Item item = character.getLocation().getItems().get(itemName);
		character.grab(item);
	}
}
