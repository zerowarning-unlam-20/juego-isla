package commands;

import java.util.Scanner;

import entities.Player;
import items.Item;
import items.properties.Inspectable;
import items.properties.Readablel;
import items.types.Text;

public class ReadCommand implements ActionCommand {
	private Player character;

	public ReadCommand(Player personaje) {
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
		Item result = character.getInventory().get(name);
		character.read(result);
	}

}
