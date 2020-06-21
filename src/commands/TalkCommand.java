package commands;

import java.util.Scanner;

import entities.Player;

public class TalkCommand implements ActionCommand {
	private Player character;

	public TalkCommand(Player character) {
		this.character = character;
	}

	@Override
	public void perform(Scanner args) {
		// TODO Auto-generated method stub
	}

}
