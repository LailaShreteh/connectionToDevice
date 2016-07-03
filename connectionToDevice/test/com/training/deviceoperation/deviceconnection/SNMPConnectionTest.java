package com.training.deviceoperation.deviceconnection;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 */
public class SNMPConnectionTest {
	private SNMPConnection SnmpClient;

	@Before
	public void setup() {
		SnmpClient = new SNMPConnection();

	}

	@Test
	public void testConnectClass_SucessCase() {
		String result = null;
		try {
			result = SnmpClient.connectToDevice("192.168.50.200", 161);
			assertNotNull(result);
			assertEquals("Sucess" , result);
			//System.out.println(":3 :3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertEquals("Fail", result);
		}

	}
	
	
	
	
	@After
	public void teardown ()
	{
		SnmpClient = null;
	}
}
