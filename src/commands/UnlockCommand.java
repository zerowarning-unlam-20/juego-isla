package commands;

import java.util.Scanner;

import entities.UserCharacter;
import items.Access;

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
		Access result = null;
		if (!toUnlock.isEmpty()) {
			for (Access acceso : character.getLocation().getAccesses().values())
				if (acceso.getName().equalsIgnoreCase(toUnlock)) {
					result = acceso;
					break;
				}
		}
		character.unlock(result);
	}
}
