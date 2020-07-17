package commands;

import java.util.Scanner;

import entities.Entity;

public class TalkCommand implements ActionCommand { // Uh falto implementar talk
	private Entity character; // TODO: Talk

	public TalkCommand(Entity character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		String objective = "";
		String aux = "";
		while (args.hasNext() && !aux.contains(",")) {
			objective += aux + " ";
			aux = args.next();
		}
		if (aux.contains(",")) {
			objective += aux.substring(0, aux.trim().length() - 1);
		} else
			objective += aux;
		objective = objective.trim();
		String message = "";
		aux = "";
		while (args.hasNext()) {
			message += aux + " ";
			aux = args.next();
		}
		message += aux;
		message = message.trim();

		character.talk(objective, message);
	}
}
