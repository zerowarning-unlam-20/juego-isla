package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manager.GameManager;
import tools.Gender;

class WorldLoaderTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() throws FileNotFoundException, IOException {
		GameManager gameManager = new GameManager(true);
		gameManager.loadGame("Blue Hawaii", "Carlos", Gender.M);
	}

}
