package commands;

import java.util.Scanner;

import entities.Access;
import entities.UserCharacter;
import entities.Item;
import entities.Location;

@Command("ver")
public class LookCommand implements ActionCommand {
	UserCharacter character;

	public LookCommand(UserCharacter character) {
		this.character = character;
	}

	@Override
	public String perform(Scanner args) {
		if (args.hasNext()) {
			String object = args.next();
			return character.look(object);
		}
		return "¿Qué querés ver?";
	}

}
