package commands;

import java.util.Map;
import java.util.Scanner;

import entities.Entity;
import entities.UserCharacter;
import island.GameObject;
import items.Access;
import items.Item;

public class LookCommand implements ActionCommand {
	private UserCharacter character;

	public LookCommand(UserCharacter character) {
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
				for (Item item : character.getInventory()) {
					if (item.getName().toLowerCase().equalsIgnoreCase(name)) {
						result = item;
					}
				}
				if (result == null) {
					for (Access acceso : character.getLocation().getAccesses().values()) {
						if (acceso.getName().equalsIgnoreCase(name) || acceso.getDescription().equalsIgnoreCase(name)) {
							result = acceso;
							break;
						}
					}
				}
				if (result == null) {
					for (Item item : character.getLocation().getItems()) {
						if (item.getName().equalsIgnoreCase(name) || item.getDescription().equalsIgnoreCase(name)) {
							result = item;
							break;
						}
					}
				}
				if (result == null) {
					for (Map.Entry<Integer, Entity> entry : character.getLocation().getEntities().entrySet()) {
						Entity entity = entry.getValue();
						if (entity.getName().toLowerCase().contentEquals(name)
								|| entity.getDescription().equalsIgnoreCase(name)) {
							result = entity;
							break;
						}
					}
				}
			}
		}
		character.look(result);
	}
}
