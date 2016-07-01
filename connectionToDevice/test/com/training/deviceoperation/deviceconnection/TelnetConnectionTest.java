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
		String result = connection.connectClass("8.8.8.8", 53);
		assertNotNull(result);
		assertEquals("Sucess" , result);
		//System.out.println(":3 :3");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConnectClass_failureCase_hostIsNULL() {
		connection.connectClass(null, 53);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConnectClass_failureCase_hostIsBlank() {
		connection.connectClass("", 53);		
	}
	
	@After
	public void teardown ()
	{
		connection = null;
	}
	

}
