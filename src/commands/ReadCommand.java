package commands;

import java.util.Scanner;

import entities.UserCharacter;
import items.Item;
import items.properties.Inspectable;
import items.properties.Readablel;
import items.types.Text;

public class ReadCommand implements ActionCommand {
	private UserCharacter character;

	public ReadCommand(UserCharacter personaje) {
		this.character = personaje;
	}

	@Override
	public void perform(Scanner args) {
		String name = "";
		while (args.hasNext()) {
			name += args.next();
		}

		read(name);
	}

	private void read(String name) {
		Item result = null;
		for (Item item : character.getInventory()) {
			if (item instanceof Readablel && item.getName().equalsIgnoreCase(name)) {
				result = item;
				break;
			}
		}
		character.read(result);
	}

}
