package commands;

import java.util.Scanner;

import entities.Player;

public class DrinkCommand implements ActionCommand {
	private Player character;

	public DrinkCommand(Player character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String itemName = "";
		String contName = "";
		String aux = "";
		while (args.hasNext() && !aux.equalsIgnoreCase("de")) {
			itemName += " " + aux;
			aux = args.next();
		}
		itemName = itemName.trim();
		while (args.hasNext()) {
			contName += " " + args.next();
		}
		contName = contName.trim();

		character.drink(itemName, contName);
	}

}
