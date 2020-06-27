package commands;

import java.util.Scanner;

import entities.Entity;

public class LookCommand implements ActionCommand {
	private Entity character;

	public LookCommand(Entity character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String object = "";
		while (args.hasNext())
			object = args.next();
		look(object);
	}

	private void look(String name) {
		if (!name.isEmpty()) {
			if (name.equalsIgnoreCase("alrededor")) {
				character.lookAround();
				return;
			} else if (name.equalsIgnoreCase("inventario")) {
				character.lookInventory();
				return;
			} else if (name.equalsIgnoreCase("estado")) {
				character.lookState();
				return;
			} else {
				character.look(name);
			}
		}
	}
}
