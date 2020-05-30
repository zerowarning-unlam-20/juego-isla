package tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import tools.IdManager;

class IdManagerTest {

	@Test
	public void idManagerTest1() {
		System.out.println("----------------TEST ID MANAGER------------------");
		IdManager.setLastId(0);
		for (int i = 0; i < 1000; i++) {
			IdManager.getNext();
		}
		Assert.assertEquals(1001, IdManager.getNext());
	}

}
