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
	public void test() {
		String result = null;
		try {
			result = SnmpClient.connectClass("127.0.0.1", 161);
			assertNotNull(result);
			// assertEquals("Sucess" , result);
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
