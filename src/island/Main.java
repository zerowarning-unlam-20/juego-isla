package island;

import manager.GameManager;

public class Main {

	public static void main(String[] args) {
		GameManager game = GameManager.getInstance();
		game.run();
	}

}
