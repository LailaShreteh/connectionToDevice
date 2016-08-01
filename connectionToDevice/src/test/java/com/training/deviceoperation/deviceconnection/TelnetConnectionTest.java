package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.training.deviceoperation.deviceconnection.ConnectionFactory;
import com.training.deviceoperation.deviceconnection.ConnectionRouter;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;

/**
 * 
 * @author user
 *
 */
public class TelnetConnectionTest {

	ConnectionFactory connectionFactory = new ConnectionFactory();
	private static ConnectionRouter connection;
	String result = null;
	ArrayList<EthernetProtocolEndpoint> epeList;

	@Before
	public void setup() {

		connection = ConnectionFactory.createConnection("TELNET");
		connection.setHost("192.168.50.200");
		connection.setPort(23);

	}

	@Test
	public void testConnectClass() {

		result = connection.connectToDevice();
		assertNotNull(result);
		assertEquals("Sucess", result);
	}

	/*@Test(expected = IllegalArgumentException.class)
	public void testFailureConnectionPort() {
		connection.setPort(98); // not the right port !!
		result = connection.connectToDevice();
		assertEquals("Fail to connect x_x", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailureConnectionHost() {
		connection.setHost("9.9.9.9"); // not our device !!
		result = connection.connectToDevice();
		assertEquals("Fail to connect x_x", result);
	}
*/
	/*@Test(expected = IllegalArgumentException.class)
	public void testHost() {
		connection.setHost(null);
		String re = connection.connectToDevice();
		// String expResult = "Fail";
		// assertEquals(expResult, re);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConnectClass_failureCase_hostIsBlank() {
		connection.setHost("");
		connection.connectToDevice();
		//connection.disconnectConnection();
	}
*/
	@AfterClass
	public static void teardown() throws IOException {
		if (connection != null) {
			connection.disconnectConnection();
			connection = null;
		}

}
}

