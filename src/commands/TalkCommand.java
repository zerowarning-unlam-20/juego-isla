package commands;

import java.util.Scanner;

import entities.UserCharacter;

public class TalkCommand implements ActionCommand {
	private UserCharacter character;

	public TalkCommand(UserCharacter character) {
		this.character = character;
	}
	
	@Override
	public void perform(Scanner args) {
		// TODO Auto-generated method stub
	}

}
