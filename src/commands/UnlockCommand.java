package commands;

import java.util.Scanner;

import entities.Player;

public class UnlockCommand implements ActionCommand {
	private Player character;

	public UnlockCommand(Player character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		if (args.hasNext()) {
			String toUnlock = "";
			String aux = "";
			toUnlock = args.next();

			while (args.hasNext() && aux != "con") {
				toUnlock += aux + " ";
				aux = args.next();
			}
			String key = "";
			while (args.hasNext()) {
				key += args.next() + " ";
			}
			key = key.trim();

			toUnlock += aux;
			toUnlock = toUnlock.trim();
			character.unlock(toUnlock.toLowerCase(), key.toLowerCase());
		}
	}
}
