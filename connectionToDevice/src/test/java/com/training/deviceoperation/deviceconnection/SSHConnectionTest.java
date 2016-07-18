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

	private Connection connection;

	@Before
	public void setup() {
		connection = new SSHConnection("192.168.50.200", 22);

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
		assertEquals("Sucess", result);
	}

	@After
	public void teardown() {
		connection = null;
		connection.disconnectConnection();
	}

}
