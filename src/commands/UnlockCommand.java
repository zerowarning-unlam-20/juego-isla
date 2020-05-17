package commands;

import java.util.Scanner;

import entities.Access;
import entities.UserCharacter;
import entities.Item;

@Command("desbloquear")
public class UnlockCommand implements ActionCommand {
	private UserCharacter character;

	public UnlockCommand(UserCharacter character) {
		this.character = character;
	}

	@Override
	public String perform(Scanner args) {
		String retorno = null;
		
		if (args.hasNext()) {
			String toUnlock = "";
			String aux = "";
			toUnlock = args.next();
			if (toUnlock == "")
				retorno = "¿Qué querés desbloquear?";
			else {
				while (args.hasNext() && aux != "con") {
					toUnlock += aux + " ";
					aux = args.next();
				}	
				toUnlock += aux;
				toUnlock = toUnlock.trim();

				retorno = character.unlock(toUnlock);
			}

		}
		retorno =  "No se desbloqueó nada";
		
		return retorno;
	}

}
