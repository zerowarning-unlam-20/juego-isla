package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tools.WorldLoader;

class WorldLoaderTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() throws FileNotFoundException, IOException {
		WorldLoader wl = new WorldLoader();
		wl.loadLocations("zones.json");
		//wl.loadEntities("entities.json");
		wl.getLocations();
	}

}
