package commands;

import java.util.Scanner;

import entities.UserCharacter;
import island.GameObject;
import items.Access;
import items.Item;

@Command("ver")
public class LookCommand implements ActionCommand {
	UserCharacter character;

	public LookCommand(UserCharacter character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		if (args.hasNext()) {
			String object = args.next();
			look(object);
		}
	}

	private void look(String itemName) {
		GameObject result = null;
		if (itemName.contentEquals("alrededor") || itemName.contentEquals(character.getLocation().getName())) {
			character.lookAround();
		} else if (itemName.contentEquals("inventario")) {
			character.lookInventory();
		} else {
			for (Item item : character.getInventory()) {
				if (item.getName().contentEquals(itemName)) {
					result = item;
				}
			}
			if (result == null) {
				for (Access acceso : character.getLocation().getAccesses()) {
					if (acceso.getName().contentEquals(itemName) || acceso.getDescription().contentEquals(itemName)) {
						result = acceso;
						break;
					}
				}
			}
			if (result == null) {
				for (Item item : character.getLocation().getItems()) {
					if (item.getName().contentEquals(itemName) || item.getDescription().contentEquals(itemName)) {
						result = item;
						break;
					}
				}
			}
		}
		character.look(result);
	}
}
