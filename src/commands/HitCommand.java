package commands;

import java.util.Map;
import java.util.Scanner;

import entities.Entity;
import entities.Player;
import island.GameObject;
import items.Item;
import items.properties.Attackable;
import items.types.Weapon;

public class HitCommand implements ActionCommand {
	private Player character;

	public HitCommand(Player character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String targetString = "";
		String aux = " ";
		while (args.hasNext()) {
			aux = args.next();
			if (aux.contentEquals("con"))
				break;
			targetString += aux;
		}
		targetString.trim();

		String weaponString = "";
		while (args.hasNext()) {
			weaponString += args.next();
		}
		GameObject target = null;
		target = character.getLocation().getEntities().get(targetString);
		if (target == null) {
			target = character.getLocation().getItems().get(targetString);
		}

		weaponString.trim();
		character.attack((Weapon) character.getInventory().get(weaponString), target);

	}

}
