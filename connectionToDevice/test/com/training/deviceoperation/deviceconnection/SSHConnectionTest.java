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

	private SSHConnection connection;

	@Before
	public void setup() {
	 connection = new SSHConnection();

	}
	
	@Test
	public void testConnectClass_SucessCase() {
		String result = null;
		try {
			result = connection.connectClass("8.8.8.8", 53);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println(":P :P LLLLLL");
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
