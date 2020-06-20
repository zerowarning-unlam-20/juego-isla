package commands;

import java.util.Scanner;

import entities.UserCharacter;
import items.Item;
import items.properties.Inspectable;

public class InspectCommand implements ActionCommand {
	private UserCharacter character;

	public InspectCommand(UserCharacter character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String name = "";
		while(args.hasNext()) {
			name += args.next();
		}
		
		inspect(name);
	}

	private void inspect(String name) {
		Item result = null;
		for(Item item : character.getLocation().getItems()) {
			if(item instanceof Inspectable && item.getName().equalsIgnoreCase(name)) {
				result = item;
				break;
			}
		}
		character.inspect(result);
	}


}
