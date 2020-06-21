package commands;

import java.util.Scanner;

import entities.Player;
import island.GameObject;

public class LookCommand implements ActionCommand {
	private Player character;

	public LookCommand(Player character) {
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
		GameObject result = null;
		if (!name.isEmpty()) {
			if (name.equalsIgnoreCase("alrededor") || name.equalsIgnoreCase(character.getLocation().getName())) {
				character.lookAround();
				return;
			} else if (name.equalsIgnoreCase("inventario")) {
				character.lookInventory();
				return;
			} else if (name.equalsIgnoreCase("estado")) {
				character.lookState();
				return;
			} else {
				result = character.getInventory().get(name);
				if (result == null) {
					result = character.getLocation().getItems().get(name);
				}
				if (result == null) {
					result = character.getLocation().getAccesses().get(name);
				}
				if (result == null) {
					result = character.getLocation().getEntities().get(name);
				}
			}
		}
		character.look(result);
	}
}
