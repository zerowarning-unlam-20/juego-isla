package commands;

import java.util.Scanner;

public class CutCommand implements ActionCommand {
	private Character character;
	
	public CutCommand(Character character) {
		this.character = character;
	}
	
	@Override
	public void perform(Scanner args) {
		// cortar [] con []
	}

}
