package com.training.deviceoperation.deviceconnection;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TelnetConnectionTest {

	ConnectionFactory connectionFactory = new ConnectionFactory();
	private Connection connection;

	@Before
	public void setup() {
		connection = ConnectionFactory.createConnection("TELNET");
		connection.setHost("192.168.50.200");
		connection.setPort(23);
		connection.connectToDevice();

		// connection = new TelnetConnection("192.168.50.200", 23);
	}

	@Test
	public void testConnectClass_SucessCase() throws IOException {
		connection.getInterfaces();
		connection.createEthernetPE();
		
	}

	// @Test
	// public void testGetVersion() {
	// String version = connection.getVersion();
	//
	//
	// }

	/*
	 * @Test(expected = IllegalArgumentException.class) public void
	 * testConnectClass_failureCase_hostIsNULL() {
	 * connection.connectToDevice(null, 53); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * testConnectClass_failureCase_hostIsBlank() {
	 * connection.connectToDevice("", 53); }
	 */

	@After
	public void teardown() {
		connection.disconnectConnection();
		connection = null;


	}

}
