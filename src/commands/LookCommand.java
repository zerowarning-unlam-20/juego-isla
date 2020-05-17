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
		String retorno = null;
		if (args.hasNext()) {
			String object = args.next();
			retorno = character.look(object);
		}
		return (retorno!=null)? retorno : "¿Qué querés ver?";
	}

}
