package commands;

import java.util.Map;
import java.util.Scanner;

import entities.Entity;
import entities.UserCharacter;
import island.GameObject;
import items.Item;
import items.properties.Attackable;
import items.types.Weapon;

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
		GameObject target = null;
		for (Map.Entry<Integer, Entity> entry : character.getLocation().getEntities().entrySet()) {
			if (entry.getValue().getName().equalsIgnoreCase(targetString)) {
				target = entry.getValue();
			}
		}
		for (Item item : character.getLocation().getItems()) {
			if (item instanceof Attackable && item.getName().equalsIgnoreCase(targetString)) {
				target = item;
			}
		}
		character.attack(weapon, target);

	}

}
