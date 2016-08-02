package com.training.databasemanager;

import org.junit.Test;

/**
 * 
 * @author user
 *
 */
public class DataBaseConnectionTest {
	@Test
	public void testConnectionToDataBase() {
		result = connection.connectToDevice();
		connection.getInterface_Policy();

	}
}
