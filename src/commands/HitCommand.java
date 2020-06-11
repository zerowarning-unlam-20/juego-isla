package commands;

import java.util.Map;
import java.util.Scanner;

import entities.Entity;
import entities.UserCharacter;
import items.Weapon;

public class HitCommand implements ActionCommand {
	private UserCharacter character;

	public HitCommand(UserCharacter character) {
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
		weaponString.trim();
		Weapon weapon = (Weapon) character.getInventoryStringMap().get(weaponString);
		if (weapon != null) {
			Entity target = null;
			for (Map.Entry<Integer, Entity> entry : character.getLocation().getEntities().entrySet()) {
				if (entry.getValue().getName().toLowerCase().contentEquals(targetString)) {
					target = entry.getValue();
				}
			}
			character.attack(weapon, target);
		}

	}

}
