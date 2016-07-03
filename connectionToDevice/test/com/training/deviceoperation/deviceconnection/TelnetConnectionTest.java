package com.training.deviceoperation.deviceconnection;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TelnetConnectionTest {
	
	private TelnetConnection connection;

	@Before
	public void setup() {
	 connection = new TelnetConnection();

	}
	
	@Test
	public void testConnectClass_SucessCase() {
		String result = connection.connectToDevice("192.168.50.200", 23);
		assertNotNull(result);
		assertEquals("Sucess" , result);
		//System.out.println(":3 :3");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConnectClass_failureCase_hostIsNULL() {
		connection.connectToDevice(null, 53);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConnectClass_failureCase_hostIsBlank() {
		connection.connectToDevice("", 53);		
	}
	
	@After
	public void teardown ()
	{
		connection = null;
	}
	

}
