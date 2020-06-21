package commands;

import java.util.Scanner;

import entities.Player;
import island.GameObject;
import items.Item;
import items.properties.Inspectable;

public class InspectCommand implements ActionCommand {
	private Player character;

	public InspectCommand(Player character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String name = "";
		while (args.hasNext()) {
			name += args.next();
		}

		Item target = null;
		target = character.getInventory().get(name);
		if (target == null) {
			target = character.getLocation().getItems().get(name);
		}
		character.inspect(target);
	}

}
