package com.training.deviceoperation.deviceconnection;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 *
 */

public class SSHConnectionTest {

	ConnectionFactory connectionFactory = new ConnectionFactory();
	private Connection connection;
	
	@Before
	public void setup() {
		connection = ConnectionFactory.createConnection("SSH");
		connection.setHost("192.168.50.200");
		connection.setPort(22);
		connection.connectToDevice();
		// connection = new SSHConnection("192.168.50.200", 22);

	}

	@Test
	public void testConnectClass_SucessCase() {
		String result = null;
		try {
			connection.getInterfaces();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}

	@After
	public void teardown() {
		connection.disconnectConnection();
		connection = null;
	}

}
