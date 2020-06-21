package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manager.Game;
import manager.GameManager;
import tools.WorldLoader;

class WorldLoaderTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() throws FileNotFoundException, IOException {
		File currentDirFile = new File("");
		String folder = currentDirFile.getAbsolutePath() + "/testGame";
		GameManager gameManager = new GameManager(true);
		gameManager.loadGame(folder);
		System.out.println("stub");
	}

}
