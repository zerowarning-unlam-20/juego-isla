package island;

import manager.GameManager;

public class Main { // Quedo main porque es mas prolijo, no me gusta ver todo pegado en gameManager

	public static void main(String[] args) {
		GameManager game = new GameManager(true, false);
		game.loadGame("Blue Hawaii");
		game.consoleRun();
	}

}