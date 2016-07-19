package com.training.deviceoperation.deviceconnection;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.training.deviceoperation.parser.enumType1;

public class TelnetConnectionTest {

	private Connection connection;
	CLIConnectionFactory connectionFactory = new CLIConnectionFactory();

	@Before
	public void setup() {
		connection = connectionFactory.getConnection("TelnetConnection");
		// connection = new TelnetConnection("192.168.50.200", 23);
	}

	@Test
	public void testConnectClass_SucessCase() throws IOException {
		connection.getInterfaces();
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
		connection = null;
		connection.disconnectConnection();

	}

}