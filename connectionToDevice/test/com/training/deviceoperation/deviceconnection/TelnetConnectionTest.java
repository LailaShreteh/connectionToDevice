package com.training.deviceoperation.deviceconnection;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Test;

public class TelnetConnectionTest {

	@Test
	public void testConnectClass() throws UnknownHostException, IOException {
		TelnetConnection connection = new TelnetConnection();
		String result = connection.connectClass("www.google.com", 23);
		assertEquals("succes" , result);
	}

}
