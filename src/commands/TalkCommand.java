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
		while (args.hasNext() && !aux.contentEquals(",")) {
			objective += aux + " ";
			aux = args.next();
		}
		objective = objective.trim();
		String message = "";
		aux = "";
		while (args.hasNext() && !aux.contentEquals(",")) {
			message += aux + " ";
			aux += args.next() + " ";
		}
		character.talk(objective, message);
	}
}
