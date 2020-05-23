package commands;

import java.util.Scanner;

import entities.UserCharacter;
import items.Access;

@Command("desbloquear")
public class UnlockCommand implements ActionCommand {
	private UserCharacter character;

	public UnlockCommand(UserCharacter character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		if (args.hasNext()) {
			String toUnlock = "";
			String aux = "";
			toUnlock = args.next();
			if (toUnlock == "")
				return;
			else {
				while (args.hasNext() && aux != "con") {
					toUnlock += aux + " ";
					aux = args.next();
				}
				toUnlock += aux;
				toUnlock = toUnlock.trim();
				unlockSearch(toUnlock);
			}
		}
	}

	private void unlockSearch(String toUnlock) {
		for (Access acceso : character.getLocation().getAccesses())
			if (acceso.getName().contentEquals(toUnlock)) {
				character.unlock(acceso);
			}
	}
}
