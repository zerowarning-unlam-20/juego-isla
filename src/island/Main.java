package island;

import manager.GameManager;
import tools.Gender;

public class Main { // Quedo main porque es mas prolijo, no me gusta ver todo pegado en gameManager

	public static void main(String[] args) {
		GameManager game = new GameManager(true, false);
		game.loadGame("Piratas", "Carlos", Gender.M);
		game.consoleRun();
	}

}