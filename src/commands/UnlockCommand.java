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
		String stringToReturn = null;

		if (args.hasNext()) {
			
			String toUnlock = "";
			String aux = "";
			toUnlock = args.next();
			
			if (toUnlock == "")
				stringToReturn = "¿Qué querés desbloquear?";
			else{
				
				while (args.hasNext() && aux != "con") {
					
					toUnlock += aux + " ";
					aux = args.next();
					
				}
				
				toUnlock += aux;
				toUnlock = toUnlock.trim();

				stringToReturn = character.unlock(toUnlock);
			}

		}
		
		return (stringToReturn != null) ? stringToReturn : "No se desbloqueó nada";
	}

}
