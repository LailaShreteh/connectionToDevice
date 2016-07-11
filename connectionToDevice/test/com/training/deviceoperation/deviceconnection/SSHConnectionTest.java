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

	private CLIConnection connection;

	@Before
	public void setup() {
	 connection = new SSHConnection();

	}
	
	@Test
	public void testConnectClass_SucessCase() {
		String result = null;
		try {
			result = connection.connectToDevice("192.168.50.200",22);
			connection.getInterfaces(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(":P :P LLLLLL");
			e.getMessage();
		}
		assertEquals("Sucess" , result);
		}
	
	@After
	public void teardown ()
	{
		connection = null;
	}

}
