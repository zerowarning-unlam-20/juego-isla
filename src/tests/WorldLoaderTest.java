package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tools.WorldLoader;

class WorldLoaderTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		WorldLoader wl = new WorldLoader();
		wl.getLocations();
	}

}
