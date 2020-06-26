package commands;

import java.util.Scanner;

import entities.Player;

public class AttackCommand implements ActionCommand {
	private Player character;

	public AttackCommand(Player character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String targetName = "";
		String aux = " ";
		while (args.hasNext()) {
			aux = args.next();
			if (aux.contentEquals("con"))
				break;
			targetName += aux;
		}
		targetName.trim();

		String weaponString = "";
		while (args.hasNext()) {
			weaponString += args.next() + " ";
		}
		weaponString = weaponString.trim();
		character.attack(weaponString, targetName);

	}

}
