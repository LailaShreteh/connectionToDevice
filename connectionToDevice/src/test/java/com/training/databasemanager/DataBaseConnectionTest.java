package com.training.databasemanager;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.training.databacemanager.DatabaseManager;
import com.training.deviceoperation.deviceconnection.ConnectionFactory;

/**
 * 
 * @author user
 *
 */
public class DataBaseConnectionTest {
	DatabaseManager conn = new 
	@Before
	public void setup() {

		connection = ConnectionFactory.createConnection("TELNET");
		connection.setHost("192.168.50.200");
		connection.setPort(23);

	}
	@Test
	public void testConnectionToDataBase() {
		result = connection.connectToDevice();
		connection.getInterface_Policy();

	}
	@AfterClass
	public static void teardown() throws IOException {
		if (connection != null) {
			connection.disconnectConnection();
			connection = null;
		}
	}
}
