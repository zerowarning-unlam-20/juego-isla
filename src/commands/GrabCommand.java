package commands;

import java.util.Scanner;

import entities.Entity;

public class GrabCommand implements ActionCommand {
	private Entity character;

	public GrabCommand(Entity character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String itemName = "";
		String aux = "";

		// Item a agarrar
		while (args.hasNext() && !aux.equalsIgnoreCase("de")) {
			itemName += aux + " ";
			aux = args.next();
		}
		if (!aux.equalsIgnoreCase("de")) {
			itemName += aux;
		}
		itemName = itemName.trim();
		if (!aux.equalsIgnoreCase("de") || !args.hasNext()) {
			character.grab(itemName);
		} else {
			// Lugar de donde se agarra
			String item2Name = "";
			aux = "";
			while (args.hasNext() && !aux.equalsIgnoreCase("con")) {
				item2Name += aux + " ";
				aux = args.next();
			}
			item2Name = item2Name.trim();
			if (!aux.equalsIgnoreCase("con") || !args.hasNext()) {
				if (!aux.equalsIgnoreCase("con")) {
					item2Name += aux;
				}
				character.grab(item2Name, itemName);
			} else {

				// Con que se agarra (Caso particular)
				String containerName = "";
				while (args.hasNext()) {
					containerName += " " + args.next();
				}
				character.grab(itemName, item2Name, containerName.trim());
			}
		}
	}

}
